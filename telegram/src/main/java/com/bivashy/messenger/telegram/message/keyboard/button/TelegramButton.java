package com.bivashy.messenger.telegram.message.keyboard.button;

import com.bivashy.messenger.common.button.DefaultButton;
import com.bivashy.messenger.telegram.message.keyboard.button.TelegramButtonAction.TelegramButtonType;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.KeyboardButton;

public class TelegramButton extends DefaultButton {
    private static final TelegramButtonAction DEFAULT_ACTION = new TelegramButtonAction(TelegramButtonType.REPLY);

    public TelegramButton(InlineKeyboardButton wrappingButton) {
        super(wrappingButton.text());
        if (wrappingButton.callbackData() != null) {
            action = new TelegramButtonAction(TelegramButtonType.CALLBACK);
            actionData = wrappingButton.callbackData();
        }
        if (wrappingButton.url() != null) {
            action = new TelegramButtonAction(TelegramButtonType.OPEN_LINK);
            actionData = wrappingButton.url();
        }
    }

    public TelegramButton(String label) {
        super(label);
    }

    public InlineKeyboardButton createInlineButton() {
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

    public KeyboardButton createReplyButton() {
        return new KeyboardButton(label);
    }

    public static class Builder extends DefaultButtonBuilder {
        public Builder(String label) {
            super(new TelegramButton(label));
        }
    }
}
