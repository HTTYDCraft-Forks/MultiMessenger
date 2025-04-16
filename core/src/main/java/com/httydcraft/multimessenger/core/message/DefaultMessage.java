package com.httydcraft.multimessenger.core.message;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.identificator.Identificator;
import com.httydcraft.multimessenger.core.keyboard.Keyboard;
import com.httydcraft.multimessenger.core.file.MessengerFile;

/**
 * Default implementation of the Message interface.
 */
//region DefaultMessage Class
public abstract class DefaultMessage implements Message {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    protected String text;
    protected MessengerFile[] files;
    protected Keyboard keyboard;
    protected Identificator replyIdentificator;

    /**
     * Constructs a DefaultMessage with the given text.
     * @param text message text
     */
    public DefaultMessage(String text) {
        Preconditions.checkNotNull(text, "Message text cannot be null");
        this.text = text;
        logger.atFine().log("DefaultMessage created with text: %s", text);
    }

    /**
     * Gets the message text.
     * @return message text
     */
    @Override
    public String getText() {
        logger.atFine().log("getText() called, returning: %s", text);
        return text;
    }

    /**
     * Gets the keyboard attached to the message.
     * @return keyboard
     */
    @Override
    public Keyboard getKeyboard() {
        logger.atFine().log("getKeyboard() called, returning: %s", keyboard);
        return keyboard;
    }

    /**
     * Attaches files to the message.
     * @param files messenger files
     * @return this message
     */
    @Override
    public Message attachFiles(MessengerFile... files) {
        Preconditions.checkNotNull(files, "Files cannot be null");
        this.files = files;
        logger.atFine().log("attachFiles() called, files attached: %s", (Object) files);
        return this;
    }

    /**
     * Sets the message to reply to.
     * @param replyIdentificator identificator to reply to
     * @return this message
     */
    @Override
    public Message reply(Identificator replyIdentificator) {
        Preconditions.checkNotNull(replyIdentificator, "Reply identificator cannot be null");
        this.replyIdentificator = replyIdentificator;
        logger.atFine().log("reply() called, replyIdentificator: %s", replyIdentificator);
        return this;
    }

    /**
     * Abstract builder for DefaultMessage.
     */
    //region DefaultMessageBuilder Class
    public static abstract class DefaultMessageBuilder implements MessageBuilder {
        private final DefaultMessage message;

        /**
         * Constructs a DefaultMessageBuilder.
         * @param message message to build
         */
        public DefaultMessageBuilder(DefaultMessage message) {
            Preconditions.checkNotNull(message, "Message cannot be null");
            this.message = message;
            logger.atFine().log("DefaultMessageBuilder created for message: %s", message);
        }

        /**
         * Sets the keyboard for the message.
         * @param keyboard keyboard to set
         * @return this builder
         */
        @Override
        public MessageBuilder keyboard(Keyboard keyboard) {
            message.keyboard = keyboard;
            logger.atFine().log("keyboard() called, set keyboard: %s", keyboard);
            return this;
        }

        /**
         * Sets the message text.
         * @param text message text
         * @return this builder
         */
        @Override
        public MessageBuilder text(String text) {
            Preconditions.checkNotNull(text, "Message text cannot be null");
            message.text = text;
            logger.atFine().log("text() called, set text: %s", text);
            return this;
        }

        /**
         * Sets the message to reply to.
         * @param messageIdentificator identificator to reply to
         * @return this builder
         */
        @Override
        public MessageBuilder reply(Identificator messageIdentificator) {
            Preconditions.checkNotNull(messageIdentificator, "Reply identificator cannot be null");
            message.reply(messageIdentificator);
            logger.atFine().log("reply() called, set replyIdentificator: %s", messageIdentificator);
            return this;
        }

        /**
         * Attaches files to the message.
         * @param files messenger files
         * @return this builder
         */
        @Override
        public MessageBuilder attachFiles(MessengerFile... files) {
            Preconditions.checkNotNull(files, "Files cannot be null");
            message.attachFiles(files);
            logger.atFine().log("attachFiles() called, files attached: %s", (Object) files);
            return this;
        }

        /**
         * Builds the message.
         * @return built message
         */
        @Override
        public Message build() {
            logger.atFine().log("build() called, returning message: %s", message);
            return message;
        }
    }
    //endregion
}
//endregion
