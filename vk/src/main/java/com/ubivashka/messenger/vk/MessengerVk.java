package com.ubivashka.messenger.vk;

import com.ubivashka.messenger.vk.message.VkMessage;
import com.ubivashka.messenger.vk.message.keyboard.VkKeyboard;
import com.ubivashka.messenger.vk.message.keyboard.button.VkButton;
import com.ubivashka.messenger.vk.message.keyboard.button.VkButtonAction.VkButtonActionBuilder;
import com.ubivashka.messenger.vk.message.keyboard.button.VkButtonColor.VkButtonColorBuilder;
import com.ubivaska.messenger.common.Messenger;
import com.ubivaska.messenger.common.button.Button.ButtonBuilder;
import com.ubivaska.messenger.common.button.ButtonAction.ButtonActionBuilder;
import com.ubivaska.messenger.common.button.ButtonColor.ButtonColorBuilder;
import com.ubivaska.messenger.common.keyboard.Keyboard.KeyboardBuilder;
import com.ubivaska.messenger.common.message.Message.MessageBuilder;

public interface MessengerVk extends Messenger {
	static final MessengerVk INSTANCE = new MessengerVk() {
	};

	@Override
	default MessageBuilder newMessageBuilder(String text) {
		return new VkMessage(text).new VkMessageBuilder();
	}

	@Override
	default ButtonBuilder newButtonBuilder(String label) {
		return new VkButton(label).new VkButtonBuilder();
	}

	@Override
	default KeyboardBuilder newKeyboardBuilder() {
		return new VkKeyboard().new VkKeyboardBuilder();
	}

	@Override
	default ButtonActionBuilder newButtonActionBuilder() {
		return new VkButtonActionBuilder();
	}

	@Override
	default ButtonColorBuilder newButtonColorBuilder() {
		return new VkButtonColorBuilder();
	}

	static MessengerVk getInstance() {
		return INSTANCE;
	}
}
