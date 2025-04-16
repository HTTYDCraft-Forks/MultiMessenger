package com.httydcraft.multimessenger.vk;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.Messenger;
import com.httydcraft.multimessenger.core.button.Button.ButtonBuilder;
import com.httydcraft.multimessenger.core.button.ButtonAction.ButtonActionBuilder;
import com.httydcraft.multimessenger.core.button.ButtonColor.ButtonColorBuilder;
import com.httydcraft.multimessenger.core.keyboard.Keyboard.KeyboardBuilder;
import com.httydcraft.multimessenger.core.message.Message.MessageBuilder;
import com.httydcraft.multimessenger.vk.message.VkMessage;
import com.httydcraft.multimessenger.vk.message.keyboard.VkKeyboard;
import com.httydcraft.multimessenger.vk.message.keyboard.button.VkButton;
import com.httydcraft.multimessenger.vk.message.keyboard.button.VkButtonAction;
import com.httydcraft.multimessenger.vk.message.keyboard.button.VkButtonColor;

/**
 * VK implementation of the Messenger interface for sending messages and building UI components.
 * 
 * This interface provides methods for creating VK-specific message builders, button builders, 
 * keyboard builders, button action builders, and button color builders. It also provides a 
 * method for getting the name of the messenger implementation.
 */
//region MessengerVk Interface
public interface MessengerVk extends Messenger {
    /**
     * Logger instance for logging actions.
     */
    GoogleLogger logger = GoogleLogger.forEnclosingClass();
    
    /**
     * Singleton instance of MessengerVk.
     */
    MessengerVk INSTANCE = new MessengerVk() {
    };

    /**
     * Returns the singleton instance of MessengerVk.
     * 
     * @return MessengerVk instance
     */
    static MessengerVk getInstance() {
        logger.atFine().log("getInstance() called");
        return INSTANCE;
    }

    /**
     * Creates a new VkMessage builder with the specified text.
     * 
     * @param text message text
     * @return MessageBuilder for VK
     * @throws NullPointerException if text is null
     */
    @Override
    default MessageBuilder newMessageBuilder(String text) {
        Preconditions.checkNotNull(text, "Message text cannot be null");
        logger.atFine().log("newMessageBuilder() called with text: %s", text);
        return new VkMessage.Builder(text);
    }

    /**
     * Creates a new VkButton builder with the specified label.
     * 
     * @param label button label
     * @return ButtonBuilder for VK
     * @throws NullPointerException if label is null
     */
    @Override
    default ButtonBuilder newButtonBuilder(String label) {
        Preconditions.checkNotNull(label, "Button label cannot be null");
        logger.atFine().log("newButtonBuilder() called with label: %s", label);
        return new VkButton.Builder(label);
    }

    /**
     * Creates a new VkKeyboard builder.
     * 
     * @return KeyboardBuilder for VK
     */
    @Override
    default KeyboardBuilder newKeyboardBuilder() {
        logger.atFine().log("newKeyboardBuilder() called");
        return new VkKeyboard.Builder();
    }

    /**
     * Creates a new VkButtonAction builder.
     * 
     * @return ButtonActionBuilder for VK
     */
    @Override
    default ButtonActionBuilder newButtonActionBuilder() {
        logger.atFine().log("newButtonActionBuilder() called");
        return new VkButtonAction.Builder();
    }

    /**
     * Creates a new VkButtonColor builder.
     * 
     * @return ButtonColorBuilder for VK
     */
    @Override
    default ButtonColorBuilder newButtonColorBuilder() {
        logger.atFine().log("newButtonColorBuilder() called");
        return new VkButtonColor.Builder();
    }

    /**
     * Gets the name of the messenger implementation.
     * 
     * @return messenger name
     */
    @Override
    default String getName() {
        logger.atFine().log("getName() called");
        return "VK";
    }
}
//endregion
