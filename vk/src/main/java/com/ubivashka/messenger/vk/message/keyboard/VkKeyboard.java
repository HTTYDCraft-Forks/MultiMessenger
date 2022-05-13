package com.ubivashka.messenger.vk.message.keyboard;

import java.util.stream.Collectors;

import com.ubivashka.messenger.vk.message.keyboard.button.VkButton;
import com.ubivaska.messenger.common.keyboard.DefaultKeyboard;

public class VkKeyboard extends DefaultKeyboard {
	public com.vk.api.sdk.objects.messages.Keyboard build() {
		com.vk.api.sdk.objects.messages.Keyboard keyboard = new com.vk.api.sdk.objects.messages.Keyboard();
		keyboard.setButtons(buttons.stream().map(listButtons -> listButtons.stream()
				.map(button -> button.as(VkButton.class).create()).collect(Collectors.toList()))
				.collect(Collectors.toList()));
		keyboard.setInline(keyboardType.isInline());
		return keyboard;
	}

	public class VkKeyboardBuilder extends DefaultKeyboardBuilder {
		public VkKeyboardBuilder() {
			super(VkKeyboard.this);
		}
	}
}
