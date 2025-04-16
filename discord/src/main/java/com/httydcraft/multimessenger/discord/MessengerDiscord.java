package com.httydcraft.multimessenger.discord;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.Messenger;
import com.httydcraft.multimessenger.core.button.Button.ButtonBuilder;
import com.httydcraft.multimessenger.core.button.ButtonAction.ButtonActionBuilder;
import com.httydcraft.multimessenger.core.button.ButtonColor.ButtonColorBuilder;
import com.httydcraft.multimessenger.core.keyboard.Keyboard.KeyboardBuilder;
import com.httydcraft.multimessenger.core.message.Message.MessageBuilder;
import com.httydcraft.multimessenger.discord.message.DiscordMessage;
import com.httydcraft.multimessenger.discord.message.keyboard.DiscordKeyboard;
import com.httydcraft.multimessenger.discord.message.keyboard.button.DiscordButton;
import com.httydcraft.multimessenger.discord.message.keyboard.button.DiscordButtonColor;

/**
 * Discord implementation of the Messenger interface.
 */
//region MessengerDiscord Interface
public interface MessengerDiscord extends Messenger {
    GoogleLogger logger = GoogleLogger.forEnclosingClass();
    MessengerDiscord INSTANCE = new MessengerDiscord() {
    };

    /**
     * Gets the singleton instance of MessengerDiscord.
     * @return MessengerDiscord instance
     */
    static MessengerDiscord getInstance() {
        logger.atFine().log("getInstance() called");
        return INSTANCE;
    }

    /**
     * Creates a new message builder with the given text.
     * @param text the text of the message
     * @return a new message builder
     */
    @Override
    default MessageBuilder newMessageBuilder(String text) {
        logger.atFine().log("newMessageBuilder() called with text: %s", text);
        return new DiscordMessage.Builder(text);
    }

    /**
     * Creates a new button builder with the given label.
     * @param label the label of the button
     * @return a new button builder
     */
    @Override
    default ButtonBuilder newButtonBuilder(String label) {
        logger.atFine().log("newButtonBuilder() called with label: %s", label);
        return new DiscordButton.Builder(label);
    }

    /**
     * Creates a new keyboard builder.
     * @return a new keyboard builder
     */
    @Override
    default KeyboardBuilder newKeyboardBuilder() {
        logger.atFine().log("newKeyboardBuilder() called");
        return new DiscordKeyboard.Builder();
    }

    /**
     * Creates a new button action builder.
     * @return a new button action builder
     */
    @Override
    default ButtonActionBuilder newButtonActionBuilder() {
        logger.atFine().log("newButtonActionBuilder() called");
        return ButtonActionBuilder.unsupportedBuilder();
    }

    /**
     * Creates a new button color builder.
     * @return a new button color builder
     */
    @Override
    default ButtonColorBuilder newButtonColorBuilder() {
        logger.atFine().log("newButtonColorBuilder() called");
        return new DiscordButtonColor.Builder();
    }

    /**
     * Gets the name of the messenger.
     * @return the name of the messenger
     */
    @Override
    default String getName() {
        logger.atFine().log("getName() called");
        return "DISCORD";
    }
}
//endregion
