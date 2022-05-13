package com.ubivashka.messenger.telegram.message.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.ubivashka.messenger.telegram.message.keyboard.button.TelegramButton;
import com.ubivaska.messenger.common.keyboard.DefaultKeyboard;

public class TelegramKeyboard extends DefaultKeyboard {
	public com.pengrad.telegrambot.model.request.Keyboard create() {
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

	public class TelegramKeyboardBuilder extends DefaultKeyboardBuilder {
		public TelegramKeyboardBuilder() {
			super(TelegramKeyboard.this);
		}
	}
}
