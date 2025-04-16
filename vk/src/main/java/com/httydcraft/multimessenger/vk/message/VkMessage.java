package com.httydcraft.multimessenger.vk.message;

import java.util.StringJoiner;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.ApiProvider;
import com.httydcraft.multimessenger.core.file.FileType;
import com.httydcraft.multimessenger.core.file.MessengerFile;
import com.httydcraft.multimessenger.core.identificator.Identificator;
import com.httydcraft.multimessenger.core.message.DefaultMessage;
import com.httydcraft.multimessenger.vk.provider.VkApiProvider;
import com.httydcraft.multimessenger.vk.message.keyboard.VkKeyboard;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.responses.PhotoUploadResponse;
import com.vk.api.sdk.objects.photos.responses.SaveMessagesPhotoResponse;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;

import io.netty.util.internal.ThreadLocalRandom;

/**
 * VK implementation of a message with support for attachments and keyboard.
 */
//region VkMessage Class
public class VkMessage extends DefaultMessage {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private static VkApiProvider defaultApiProvider;

    /**
     * Constructs a VkMessage with the given text.
     * @param text message text
     */
    public VkMessage(String text) {
        super(text);
        logger.atFine().log("VkMessage created with text: %s", text);
    }

    /**
     * Sends the message using the default API provider.
     * @param identificator recipient identificator
     */
    @Override
    public void send(Identificator identificator) {
        Preconditions.checkNotNull(defaultApiProvider, "Default vk api provider was not defined. Define with static VkMessage#setDefaultApiProvider method!");
        send(identificator, defaultApiProvider);
    }

    /**
     * Sends the message using the specified API provider.
     * @param identificator recipient identificator
     * @param apiProvider API provider
     */
    @Override
    public void send(Identificator identificator, ApiProvider apiProvider) {
        Preconditions.checkArgument(identificator.isNumber(), "Cannot send message to the not number id");
        VkApiProvider vkApiProvider = apiProvider.as(VkApiProvider.class);
        MessagesSendQuery sendQuery = vkApiProvider.getClient().messages().send(vkApiProvider.getActor())
                .peerId((int) identificator.asNumber()).randomId(ThreadLocalRandom.current().nextInt()).message(text);
        if (keyboard != null && keyboard.safeAs(VkKeyboard.class).isPresent())
            sendQuery.keyboard(keyboard.as(VkKeyboard.class).create());
        if (replyIdentificator != null && replyIdentificator.isNumber())
            sendQuery.replyTo((int) replyIdentificator.asNumber());
        if (files != null && files.length != 0)
            sendQuery.attachment(toAttachment(files, vkApiProvider));

        try {
            sendQuery.execute();
            logger.atInfo().log("Message sent to peer: %s", identificator);
        } catch(ApiException | ClientException e) {
            logger.atSevere().withCause(e).log("Failed to send message to peer: %s", identificator);
            e.printStackTrace();
        }
    }

    private String toAttachment(MessengerFile[] files, VkApiProvider vkApiProvider) {
        StringJoiner joiner = new StringJoiner(",");
        for (MessengerFile file : files) {
            String rawAttachment = toAttachment(file, vkApiProvider);
            if (rawAttachment != null)
                joiner.add(rawAttachment);
        }
        return joiner.toString();
    }

    private String toAttachment(MessengerFile file, VkApiProvider vkApiProvider) {
        if (file.getFileType() != FileType.PHOTO)// You can attach photo only in vk api
            return null;
        try {
            String uploadUrl = vkApiProvider.getClient().photos().getMessagesUploadServer(vkApiProvider.getActor())
                    .execute().getUploadUrl().toString();
            PhotoUploadResponse photoUploadResponse = vkApiProvider.getClient().upload()
                    .photo(uploadUrl, file.getFile()).execute();
            SaveMessagesPhotoResponse savePhotoResponse = vkApiProvider.getClient().photos()
                    .saveMessagesPhoto(vkApiProvider.getActor(), photoUploadResponse.getPhoto())
                    .server(photoUploadResponse.getServer()).hash(photoUploadResponse.getHash()).execute().get(0);
            return "photo" + savePhotoResponse.getOwnerId() + "_" + savePhotoResponse.getId();
        } catch(ClientException | ApiException e) {
            logger.atSevere().withCause(e).log("Failed to upload attachment for file: %s", file);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the default VK API provider.
     * @return default VkApiProvider
     */
    public static VkApiProvider getDefaultApiProvider() {
        return defaultApiProvider;
    }

    /**
     * Sets the default VK API provider.
     * @param defaultApiProvider VkApiProvider instance
     */
    public static void setDefaultApiProvider(VkApiProvider defaultApiProvider) {
        VkMessage.defaultApiProvider = defaultApiProvider;
        logger.atFine().log("DefaultApiProvider set: %s", defaultApiProvider);
    }

    /**
     * Builder for VkMessage.
     */
    public static class Builder extends DefaultMessageBuilder {
        public Builder(String text) {
            super(new VkMessage(text));
        }
    }
}
//endregion
