package com.bivashy.messenger.vk.message.keyboard;

import java.util.Optional;
import java.util.stream.Collectors;

import com.bivashy.messenger.common.button.Button;
import com.bivashy.messenger.common.keyboard.DefaultKeyboard;
import com.bivashy.messenger.vk.message.keyboard.button.VkButton;

public class VkKeyboard extends DefaultKeyboard {
    public VkKeyboard() {
    }

    public VkKeyboard(com.vk.api.sdk.objects.messages.Keyboard wrappingKeyboard) {
        this.buttons = wrappingKeyboard
                .getButtons().stream().map(listButtons -> listButtons.stream()
                        .map(button -> new VkButton(button).as(Button.class)).collect(Collectors.toList()))
                .collect(Collectors.toList());
        this.keyboardType = Optional.ofNullable(wrappingKeyboard.getInline()).orElse(false) ? KeyboardType.inline() : KeyboardType.reply();
    }

    public com.vk.api.sdk.objects.messages.Keyboard create() {
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
