package com.bivashy.messenger.vk.message;

import java.util.StringJoiner;

import com.bivashy.messenger.common.ApiProvider;
import com.bivashy.messenger.common.file.FileType;
import com.bivashy.messenger.common.file.MessengerFile;
import com.bivashy.messenger.common.identificator.Identificator;
import com.bivashy.messenger.common.message.DefaultMessage;
import com.bivashy.messenger.vk.provider.VkApiProvider;
import com.bivashy.messenger.vk.message.keyboard.VkKeyboard;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.responses.PhotoUploadResponse;
import com.vk.api.sdk.objects.photos.responses.SaveMessagesPhotoResponse;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;

import io.netty.util.internal.ThreadLocalRandom;

public class VkMessage extends DefaultMessage {
    private static VkApiProvider defaultApiProvider;

    public VkMessage(String text) {
        super(text);
    }

    @Override
    public void send(Identificator identificator) {
        if (defaultApiProvider == null)
            throw new NullPointerException(
                    "Default vk api provider was not defined. Define with static VkMessage#setDefaultApiProvider method!");
        send(identificator, defaultApiProvider);
    }

    @Override
    public void send(Identificator identificator, ApiProvider apiProvider) {
        if (!identificator.isNumber())
            throw new IllegalArgumentException("Cannot send message to the not number id");
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
        } catch(ApiException | ClientException e) {
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
            e.printStackTrace();
        }
        return null;
    }

    public static VkApiProvider getDefaultApiProvider() {
        return defaultApiProvider;
    }

    public static void setDefaultApiProvider(VkApiProvider defaultApiProvider) {
        VkMessage.defaultApiProvider = defaultApiProvider;
    }

    public static class Builder extends DefaultMessageBuilder {
        public Builder(String text) {
            super(new VkMessage(text));
        }
    }
}
