package com.httydcraft.multimessenger.core.message;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.function.Castable;
import com.httydcraft.multimessenger.core.identificator.Identificator;
import com.httydcraft.multimessenger.core.keyboard.Keyboard;
import com.httydcraft.multimessenger.core.ApiProvider;
import com.httydcraft.multimessenger.core.file.MessengerFile;

/**
 * Interface for messages in the messenger UI.
 */
//region Message Interface
public interface Message extends Castable<Message> {
    /**
     * Returns only message text.
     * @return message text
     */
    String getText();

    /**
     * Gets the keyboard attached to the message.
     * @return keyboard
     */
    Keyboard getKeyboard();

    /**
     * Attaches files to the message.
     * @param files messenger files
     * @return this message
     */
    Message attachFiles(MessengerFile... files);

    /**
     * Sets the message to reply to.
     * @param messageIdentificator identificator to reply to
     * @return this message
     */
    Message reply(Identificator messageIdentificator);

    /**
     * Sends the message to the given identificator.
     * @param identificator identificator to send to
     */
    void send(Identificator identificator);

    /**
     * Sends the message to the given identificator using the provided API provider.
     * @param identificator identificator to send to
     * @param apiProvider API provider to use
     */
    void send(Identificator identificator, ApiProvider apiProvider);

    /**
     * Builder for Message implementations.
     */
    //region MessageBuilder Interface
    interface MessageBuilder {
        /**
         * Sets the keyboard for the message.
         * @param keyboard keyboard to set
         * @return this builder
         */
        MessageBuilder keyboard(Keyboard keyboard);

        /**
         * Sets the message text.
         * @param text message text
         * @return this builder
         */
        MessageBuilder text(String text);

        /**
         * Sets the message to reply to.
         * @param messageIdentificator identificator to reply to
         * @return this builder
         */
        MessageBuilder reply(Identificator messageIdentificator);

        /**
         * Attaches files to the message.
         * @param files messenger files
         * @return this builder
         */
        MessageBuilder attachFiles(MessengerFile... files);

        /**
         * Builds the message.
         * @return built message
         */
        Message build();
    }
    //endregion
}
//endregion
