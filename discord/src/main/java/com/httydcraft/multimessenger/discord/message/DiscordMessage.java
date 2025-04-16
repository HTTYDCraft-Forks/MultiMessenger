package com.httydcraft.multimessenger.discord.message;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.httydcraft.multimessenger.core.ApiProvider;
import com.httydcraft.multimessenger.core.file.MessengerFile;
import com.httydcraft.multimessenger.core.identificator.Identificator;
import com.httydcraft.multimessenger.core.message.DefaultMessage;
import com.httydcraft.multimessenger.discord.message.keyboard.DiscordKeyboard;
import com.httydcraft.multimessenger.discord.provider.DiscordApiProvider;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.interactions.callbacks.IReplyCallback;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

/**
 * Discord implementation of the Message interface.
 */
//region DiscordMessage Class
public class DiscordMessage extends DefaultMessage {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private static DiscordApiProvider defaultApiProvider;

    /**
     * Constructs a DiscordMessage with the given text.
     * @param text message text
     */
    public DiscordMessage(String text) {
        super(text);
        logger.atFine().log("DiscordMessage created with text: %s", text);
    }

    /**
     * Gets the default DiscordApiProvider.
     * @return default DiscordApiProvider
     */
    public static DiscordApiProvider getDefaultApiProvider() {
        logger.atFine().log("getDefaultApiProvider() called");
        return defaultApiProvider;
    }

    /**
     * Sets the default DiscordApiProvider.
     * @param defaultApiProvider provider to set
     */
    public static void setDefaultApiProvider(DiscordApiProvider defaultApiProvider) {
        DiscordMessage.defaultApiProvider = defaultApiProvider;
        logger.atFine().log("setDefaultApiProvider() called: %s", defaultApiProvider);
    }

    /**
     * Sends the message using the default DiscordApiProvider.
     * @param identificator identificator to send to
     */
    @Override
    public void send(Identificator identificator) {
        logger.atFine().log("send(Identificator) called: %s", identificator);
        Preconditions.checkNotNull(defaultApiProvider, "Default discord api provider was not defined. Define with static DiscordMessage#setDefaultApiProvider method!");
        send(identificator, defaultApiProvider);
    }

    /**
     * Sends the message using the provided ApiProvider.
     * @param identificator identificator to send to
     * @param apiProvider API provider to use
     */
    @Override
    public void send(Identificator identificator, ApiProvider apiProvider) {
        logger.atFine().log("send(Identificator, ApiProvider) called: %s, %s", identificator, apiProvider);
        Preconditions.checkNotNull(apiProvider, "ApiProvider cannot be null");
        JDA jda = apiProvider.as(DiscordApiProvider.class).getJDA();
        MessageChannel channel = null;
        if (identificator.isNumber())
            channel = jda.getChannelById(MessageChannel.class, identificator.asNumber());
        if (identificator.isString())
            channel = jda.getChannelById(MessageChannel.class, identificator.asString());
        if (channel == null) {
            logger.atWarning().log("Channel not found for identificator: %s", identificator);
            return;
        }
        send(channel);
    }

    /**
     * Sends the message to a MessageChannel.
     * @param channel message channel
     */
    public void send(MessageChannel channel) {
        logger.atFine().log("send(MessageChannel) called: %s", channel);
        Preconditions.checkNotNull(channel, "MessageChannel cannot be null");
        send(builder -> channel.sendMessage(builder.build()).queue());
    }

    /**
     * Sends the message as a reply using IReplyCallback.
     * @param reply reply callback
     */
    public void send(IReplyCallback reply) {
        logger.atFine().log("send(IReplyCallback) called: %s", reply);
        Preconditions.checkNotNull(reply, "IReplyCallback cannot be null");
        send(builder -> reply.reply(builder.build()).queue());
    }

    /**
     * Sends the message using a custom consumer of MessageCreateBuilder.
     * @param consumer consumer to accept the built message
     */
    public void send(Consumer<MessageCreateBuilder> consumer) {
        logger.atFine().log("send(Consumer) called");
        Preconditions.checkNotNull(consumer, "Consumer cannot be null");
        MessageCreateBuilder messageCreateBuilder = new MessageCreateBuilder().setContent(text);
        if (files != null)
            messageCreateBuilder.setFiles(Arrays.stream(files).map(MessengerFile::getFile).map(FileUpload::fromData).collect(Collectors.toList()));
        if (keyboard != null)
            keyboard.as(DiscordKeyboard.class).create().forEach(actionRow -> messageCreateBuilder.addActionRow(actionRow.getComponents()));
        consumer.accept(messageCreateBuilder);
    }

    /**
     * Builder for DiscordMessage.
     */
    //region Builder Class
    public static class Builder extends DefaultMessageBuilder {
        /**
         * Constructs a Builder for DiscordMessage.
         * @param text message text
         */
        public Builder(String text) {
            super(new DiscordMessage(text));
            logger.atFine().log("DiscordMessage.Builder created with text: %s", text);
        }
    }
    //endregion
}
//endregion
