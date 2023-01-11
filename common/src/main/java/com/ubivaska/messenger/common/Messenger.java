package com.ubivaska.messenger.common;

import com.ubivaska.messenger.common.button.Button.ButtonBuilder;
import com.ubivaska.messenger.common.button.ButtonAction.ButtonActionBuilder;
import com.ubivaska.messenger.common.button.ButtonColor.ButtonColorBuilder;
import com.ubivaska.messenger.common.keyboard.Keyboard.KeyboardBuilder;
import com.ubivaska.messenger.common.message.Message.MessageBuilder;

public interface Messenger {
	MessageBuilder newMessageBuilder(String text);

	ButtonBuilder newButtonBuilder(String label);

	KeyboardBuilder newKeyboardBuilder();

	ButtonActionBuilder newButtonActionBuilder();

	ButtonColorBuilder newButtonColorBuilder();

	String getName();
}
