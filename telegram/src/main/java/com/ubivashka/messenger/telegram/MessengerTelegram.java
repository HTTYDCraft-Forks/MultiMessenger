package com.ubivashka.messenger.telegram;

import com.ubivashka.messenger.telegram.message.TelegramMessage;
import com.ubivashka.messenger.telegram.message.keyboard.TelegramKeyboard;
import com.ubivashka.messenger.telegram.message.keyboard.button.TelegramButton;
import com.ubivashka.messenger.telegram.message.keyboard.button.TelegramButtonAction.TelegramButtonActionBuilder;
import com.ubivaska.messenger.common.Messenger;
import com.ubivaska.messenger.common.button.Button.ButtonBuilder;
import com.ubivaska.messenger.common.button.ButtonAction.ButtonActionBuilder;
import com.ubivaska.messenger.common.button.ButtonColor.ButtonColorBuilder;
import com.ubivaska.messenger.common.keyboard.Keyboard.KeyboardBuilder;
import com.ubivaska.messenger.common.message.Message.MessageBuilder;

public interface MessengerTelegram extends Messenger {
	MessengerTelegram INSTANCE = new MessengerTelegram() {
	};

	@Override
	default MessageBuilder newMessageBuilder(String text) {
		return new TelegramMessage(text).new TelegramMessageBuilder();
	}

	@Override
	default ButtonBuilder newButtonBuilder(String label) {
		return new TelegramButton(label).new TelegramButtonBuilder();
	}

	@Override
	default KeyboardBuilder newKeyboardBuilder() {
		return new TelegramKeyboard().new TelegramKeyboardBuilder();
	}

	@Override
	default ButtonActionBuilder newButtonActionBuilder() {
		return new TelegramButtonActionBuilder();
	}

	@Override
	default ButtonColorBuilder newButtonColorBuilder() {
		return ButtonColorBuilder.unsupportedBuilder();
	}

	@Override
	default String getName() {
		return "TELEGRAM";
	}

	static MessengerTelegram getInstance() {
		return INSTANCE;
	}
}
