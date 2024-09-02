package com.bivashy.messenger.vk.message;

import java.util.concurrent.ThreadLocalRandom;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import com.bivashy.messenger.common.ApiProvider;
import com.bivashy.messenger.common.file.MessengerFile;
import com.bivashy.messenger.common.identificator.Identificator;
import com.bivashy.messenger.common.message.DefaultMessage;
import com.bivashy.messenger.vk.provider.VkApiProvider;
import com.bivashy.messenger.vk.message.keyboard.VkKeyboard;

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
        Send send = vkApiProvider.vk().messages.send()
                .setPeerId((int) identificator.asNumber())
                .setRandomId(ThreadLocalRandom.current().nextInt())
                .setMessage(text);
        if (keyboard != null && keyboard.safeAs(VkKeyboard.class).isPresent())
            send.setKeyboard(keyboard.as(VkKeyboard.class).create());
        if (replyIdentificator != null && replyIdentificator.isNumber())
            send.setReplyTo((int) replyIdentificator.asNumber());
        if (files != null && files.length != 0)
            addAttachments(send, files);

        try {
            send.execute();
        } catch (VkApiException e) {
            // TODO: Proper error logging
            e.printStackTrace();
        }
    }

    private void addAttachments(Send send, MessengerFile[] files) {
        for (MessengerFile file : files) {
            switch (file.getFileType()) {
                case PHOTO:
                    send.addPhoto(file.getFile());
                    break;
                case VIDEO:
                case AUDIO:
                case DOCUMENT:
                case OTHER:
                    send.addDoc(file.getFile());
                    break;
            }
        }
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
