package com.httydcraft.multimessenger.telegram.message.keyboard.button;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.button.DefaultButton;
import com.httydcraft.multimessenger.telegram.message.keyboard.button.TelegramButtonAction.TelegramButtonType;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.KeyboardButton;

/**
 * Telegram implementation of the DefaultButton.
 */
//region TelegramButton Class
public class TelegramButton extends DefaultButton {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private static final TelegramButtonAction DEFAULT_ACTION = new TelegramButtonAction(TelegramButtonType.REPLY);

    /**
     * Constructs a TelegramButton from an InlineKeyboardButton.
     * @param wrappingButton InlineKeyboardButton to wrap
     */
    public TelegramButton(InlineKeyboardButton wrappingButton) {
        super(wrappingButton.text());
        Preconditions.checkNotNull(wrappingButton, "InlineKeyboardButton cannot be null");
        if (wrappingButton.callbackData() != null) {
            action = new TelegramButtonAction(TelegramButtonType.CALLBACK);
            actionData = wrappingButton.callbackData();
        }
        if (wrappingButton.url() != null) {
            action = new TelegramButtonAction(TelegramButtonType.OPEN_LINK);
            actionData = wrappingButton.url();
        }
        logger.atFine().log("TelegramButton created from InlineKeyboardButton: %s", wrappingButton);
    }

    /**
     * Constructs a TelegramButton with the given label.
     * @param label button label
     */
    public TelegramButton(String label) {
        super(label);
        logger.atFine().log("TelegramButton created with label: %s", label);
    }

    /**
     * Creates an InlineKeyboardButton for Telegram.
     * @return InlineKeyboardButton
     */
    public InlineKeyboardButton createInlineButton() {
        logger.atFine().log("createInlineButton() called");
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton(label);
        TelegramButtonType buttonType = action.safeAs(TelegramButtonAction.class, DEFAULT_ACTION).getButtonType();
        switch (buttonType) {
            case CALLBACK:
                inlineKeyboardButton.callbackData(actionData);
                break;
            case OPEN_LINK:
                inlineKeyboardButton.url(actionData);
                break;
            default:
                break;
        }
        return inlineKeyboardButton;
    }

    /**
     * Creates a KeyboardButton for Telegram reply keyboard.
     * @return KeyboardButton
     */
    public KeyboardButton createReplyButton() {
        logger.atFine().log("createReplyButton() called");
        return new KeyboardButton(label);
    }

    /**
     * Builder for TelegramButton.
     */
    //region Builder Class
    public static class Builder extends DefaultButtonBuilder {
        /**
         * Constructs a Builder for TelegramButton.
         * @param label button label
         */
        public Builder(String label) {
            super(new TelegramButton(label));
            logger.atFine().log("TelegramButton.Builder created with label: %s", label);
        }
    }
    //endregion
}
//endregion
