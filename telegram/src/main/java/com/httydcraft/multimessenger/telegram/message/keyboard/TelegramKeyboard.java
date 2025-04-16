package com.httydcraft.multimessenger.telegram.message.keyboard;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.httydcraft.multimessenger.core.button.Button;
import com.httydcraft.multimessenger.core.keyboard.DefaultKeyboard;
import com.httydcraft.multimessenger.telegram.message.keyboard.button.TelegramButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

/**
 * Telegram implementation of the Keyboard interface.
 */
//region TelegramKeyboard Class
public class TelegramKeyboard extends DefaultKeyboard {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Constructs an empty TelegramKeyboard.
     */
    public TelegramKeyboard() {
        logger.atFine().log("TelegramKeyboard created (empty)");
    }

    /**
     * Constructs a TelegramKeyboard from an InlineKeyboardMarkup.
     * @param wrappingKeyboard InlineKeyboardMarkup to wrap
     */
    public TelegramKeyboard(InlineKeyboardMarkup wrappingKeyboard) {
        Preconditions.checkNotNull(wrappingKeyboard, "InlineKeyboardMarkup cannot be null");
        this.buttons = Arrays.stream(wrappingKeyboard.inlineKeyboard())
                .map(buttonsArray -> Arrays.stream(buttonsArray)
                        .map(button -> new TelegramButton(button).as(Button.class)).collect(Collectors.toList()))
                .collect(Collectors.toList());
        this.keyboardType = KeyboardType.inline();
        logger.atFine().log("TelegramKeyboard created from InlineKeyboardMarkup");
    }

    /**
     * Creates a Telegram keyboard markup.
     * @return Telegram keyboard markup
     */
    public com.pengrad.telegrambot.model.request.Keyboard create() {
        logger.atFine().log("create() called");
        if (keyboardType.isInline()) {
            return new InlineKeyboardMarkup(buttons.stream()
                    .map(buttonList -> buttonList.stream()
                            .map(button -> button.as(TelegramButton.class).createInlineButton())
                            .toArray(InlineKeyboardButton[]::new))
                    .toArray(InlineKeyboardButton[][]::new));
        }
        if (keyboardType.isReply()) {
            return new ReplyKeyboardMarkup(buttons.stream()
                    .map(buttonList -> buttonList.stream()
                            .map(button -> button.as(TelegramButton.class).createReplyButton())
                            .toArray(KeyboardButton[]::new))
                    .toArray(KeyboardButton[][]::new));
        }
        return null;
    }

    /**
     * Builder for TelegramKeyboard.
     */
    //region Builder Class
    public static class Builder extends DefaultKeyboardBuilder {
        /**
         * Constructs a Builder for TelegramKeyboard.
         */
        public Builder() {
            super(new TelegramKeyboard());
            logger.atFine().log("TelegramKeyboard.Builder created");
        }
    }
    //endregion
}
//endregion
