package com.httydcraft.multimessenger.core;

import com.httydcraft.multimessenger.core.button.Button.ButtonBuilder;
import com.httydcraft.multimessenger.core.button.ButtonAction.ButtonActionBuilder;
import com.httydcraft.multimessenger.core.button.ButtonColor.ButtonColorBuilder;
import com.httydcraft.multimessenger.core.keyboard.Keyboard.KeyboardBuilder;
import com.httydcraft.multimessenger.core.message.Message.MessageBuilder;

/**
 * Interface for messenger implementations to provide message, button, keyboard, and color builders.
 */
//region Messenger Interface
public interface Messenger {
    /**
     * Creates a new message builder.
     * @param text the message text
     * @return MessageBuilder instance
     */
    MessageBuilder newMessageBuilder(String text);

    /**
     * Creates a new button builder.
     * @param label the button label
     * @return ButtonBuilder instance
     */
    ButtonBuilder newButtonBuilder(String label);

    /**
     * Creates a new keyboard builder.
     * @return KeyboardBuilder instance
     */
    KeyboardBuilder newKeyboardBuilder();

    /**
     * Creates a new button action builder.
     * @return ButtonActionBuilder instance
     */
    ButtonActionBuilder newButtonActionBuilder();

    /**
     * Creates a new button color builder.
     * @return ButtonColorBuilder instance
     */
    ButtonColorBuilder newButtonColorBuilder();

    /**
     * Gets the name of the messenger implementation.
     * @return messenger name
     */
    String getName();
}
//endregion
