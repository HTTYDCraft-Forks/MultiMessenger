package com.httydcraft.multimessenger.telegram.message;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.httydcraft.multimessenger.core.ApiProvider;
import com.httydcraft.multimessenger.core.file.MessengerFile;
import com.httydcraft.multimessenger.core.identificator.Identificator;
import com.httydcraft.multimessenger.core.message.DefaultMessage;
import com.httydcraft.multimessenger.telegram.message.keyboard.TelegramKeyboard;
import com.httydcraft.multimessenger.telegram.providers.TelegramApiProvider;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendAudio;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SendVideo;
import com.pengrad.telegrambot.response.SendResponse;

/**
 * Telegram implementation of the Message interface.
 */
//region TelegramMessage Class
public class TelegramMessage extends DefaultMessage {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private static TelegramApiProvider defaultApiProvider;

    /**
     * Constructs a TelegramMessage with the given text.
     * @param text message text
     */
    public TelegramMessage(String text) {
        super(text);
        logger.atFine().log("TelegramMessage created with text: %s", text);
    }

    /**
     * Gets the default TelegramApiProvider.
     * @return default TelegramApiProvider
     */
    public static TelegramApiProvider getDefaultApiProvider() {
        logger.atFine().log("getDefaultApiProvider() called");
        return defaultApiProvider;
    }

    /**
     * Sets the default TelegramApiProvider.
     * @param defaultApiProvider provider to set
     */
    public static void setDefaultApiProvider(TelegramApiProvider defaultApiProvider) {
        TelegramMessage.defaultApiProvider = defaultApiProvider;
        logger.atFine().log("setDefaultApiProvider() called: %s", defaultApiProvider);
    }

    /**
     * Sends the message using the default TelegramApiProvider.
     * @param identificator identificator to send to
     */
    @Override
    public void send(Identificator identificator) {
        logger.atFine().log("send(Identificator) called: %s", identificator);
        Preconditions.checkNotNull(defaultApiProvider, "Default telegram api provider was not defined. Define with static TelegramMessage#setDefaultApiProvider method!");
        send(identificator, defaultApiProvider);
    }

    /**
     * Sends the message using the default TelegramApiProvider and response consumer.
     * @param identificator identificator to send to
     * @param responseConsumer consumer for send response
     */
    public void send(Identificator identificator, Consumer<SendResponse> responseConsumer) {
        logger.atFine().log("send(Identificator, Consumer) called: %s", identificator);
        Preconditions.checkNotNull(defaultApiProvider, "Default telegram api provider was not defined. Define with static TelegramMessage#setDefaultApiProvider method!");
        send(identificator, defaultApiProvider, responseConsumer);
    }

    /**
     * Sends the message using the provided ApiProvider.
     * @param identificator identificator to send to
     * @param apiProvider API provider to use
     */
    @Override
    public void send(Identificator identificator, ApiProvider apiProvider) {
        logger.atFine().log("send(Identificator, ApiProvider) called: %s, %s", identificator, apiProvider);
        send(identificator, apiProvider, (ignored) -> {});
    }

    /**
     * Sends the message using the provided ApiProvider and response consumer.
     * @param identificator identificator to send to
     * @param apiProvider API provider to use
     * @param responseConsumer consumer for send response
     */
    public void send(Identificator identificator, ApiProvider apiProvider, Consumer<SendResponse> responseConsumer) {
        logger.atFine().log("send(Identificator, ApiProvider, Consumer) called: %s, %s", identificator, apiProvider);
        Preconditions.checkNotNull(apiProvider, "ApiProvider cannot be null");
        Preconditions.checkNotNull(identificator, "Identificator cannot be null");
        Preconditions.checkNotNull(responseConsumer, "Consumer cannot be null");
        SendMessage sendMessage = new SendMessage(identificator.asObject(), text);
        if (keyboard != null && keyboard.safeAs(TelegramKeyboard.class).isPresent())
            sendMessage.replyMarkup(keyboard.as(TelegramKeyboard.class).create());
        if (replyIdentificator != null && replyIdentificator.isNumber())
            sendMessage.replyToMessageId((int) replyIdentificator.asNumber());
        apiProvider.as(TelegramApiProvider.class).getBot().execute(sendMessage,
                new Callback<SendMessage, SendResponse>() {
                    @Override
                    public void onResponse(SendMessage request, SendResponse response) {
                        responseConsumer.accept(response);
                    }

                    @Override
                    public void onFailure(SendMessage request, IOException e) {
                        logger.atSevere().withCause(e).log("Failed to send message: %s", request);
                    }
                });
        if (files != null && files.length != 0)
            toEntities(identificator, files)
                    .forEach(request -> apiProvider.as(TelegramApiProvider.class).getBot().execute(request));
    }

    /**
     * Converts MessengerFiles to Telegram API requests.
     * @param identificator identificator to send to
     * @param files files to attach
     * @return list of API requests
     */
    private List<BaseRequest<?, ?>> toEntities(Identificator identificator, MessengerFile... files) {
        logger.atFine().log("toEntities() called: %s", (Object) files);
        List<BaseRequest<?, ?>> requests = new ArrayList<>();
        for (MessengerFile file : files) {
            BaseRequest<?, ?> request = toRequest(identificator, file);
            if (request != null)
                requests.add(request);
        }
        return requests;
    }

    /**
     * Converts a MessengerFile to a Telegram API request.
     * @param identificator identificator to send to
     * @param file file to attach
     * @return API request or null if unsupported
     */
    private BaseRequest<?, ?> toRequest(Identificator identificator, MessengerFile file) {
        logger.atFine().log("toRequest() called: %s", file);
        switch (file.getFileType()) {
            case AUDIO:
                return new SendAudio(identificator.asObject(), file.getFile());
            case DOCUMENT:
                return new SendDocument(identificator.asObject(), file.getFile());
            case PHOTO:
                return new SendPhoto(identificator.asObject(), file.getFile());
            case VIDEO:
                return new SendVideo(identificator.asObject(), file.getFile());
            default:
                logger.atWarning().log("Unsupported file type: %s", file.getFileType());
                return null;
        }
    }

    /**
     * Builder for TelegramMessage.
     */
    //region Builder Class
    public static class Builder extends DefaultMessageBuilder {
        /**
         * Constructs a Builder for TelegramMessage.
         * @param text message text
         */
        public Builder(String text) {
            super(new TelegramMessage(text));
            logger.atFine().log("TelegramMessage.Builder created with text: %s", text);
        }
    }
    //endregion
}
//endregion
