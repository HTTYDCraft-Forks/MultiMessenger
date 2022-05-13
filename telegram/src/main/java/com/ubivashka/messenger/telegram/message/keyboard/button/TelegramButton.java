package com.ubivashka.messenger.telegram.message.keyboard.button;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.ubivashka.messenger.telegram.message.keyboard.button.TelegramButtonAction.TelegramButtonType;
import com.ubivaska.messenger.common.button.DefaultButton;

public class TelegramButton extends DefaultButton {
	private static final TelegramButtonAction DEFAULT_ACTION = new TelegramButtonAction(TelegramButtonType.REPLY);

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

	public class TelegramButtonBuilder extends DefaultButtonBuilder {
		public TelegramButtonBuilder() {
			super(TelegramButton.this);
		}
	}
}
