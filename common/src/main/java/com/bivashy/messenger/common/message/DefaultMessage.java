package com.bivashy.messenger.common.message;

import com.bivashy.messenger.common.identificator.Identificator;
import com.bivashy.messenger.common.keyboard.Keyboard;
import com.bivashy.messenger.common.file.MessengerFile;

public abstract class DefaultMessage implements Message {
    protected String text;
    protected MessengerFile[] files;
    protected Keyboard keyboard;
    protected Identificator replyIdentificator;

    public DefaultMessage(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Keyboard getKeyboard() {
        return keyboard;
    }

    @Override
    public Message attachFiles(MessengerFile... files) {
        this.files = files;
        return this;
    }

    @Override
    public Message reply(Identificator replyIdentificator) {
        this.replyIdentificator = replyIdentificator;
        return this;
    }

    public static abstract class DefaultMessageBuilder implements MessageBuilder {
        private final DefaultMessage message;

        public DefaultMessageBuilder(DefaultMessage message) {
            this.message = message;
        }

        @Override
        public MessageBuilder keyboard(Keyboard keyboard) {
            message.keyboard = keyboard;
            return this;
        }

        @Override
        public MessageBuilder text(String text) {
            message.text = text;
            return this;
        }

        @Override
        public MessageBuilder reply(Identificator messageIdentificator) {
            message.reply(messageIdentificator);
            return this;
        }

        @Override
        public MessageBuilder attachFiles(MessengerFile... files) {
            message.attachFiles(files);
            return this;
        }

        @Override
        public Message build() {
            return message;
        }
    }
}
