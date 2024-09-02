package com.bivashy.messenger.vk.message.keyboard;

import java.util.Optional;
import java.util.stream.Collectors;

import api.longpoll.bots.model.objects.additional.Keyboard;
import com.bivashy.messenger.common.button.Button;
import com.bivashy.messenger.common.keyboard.DefaultKeyboard;
import com.bivashy.messenger.vk.message.keyboard.button.VkButton;

public class VkKeyboard extends DefaultKeyboard {

    public VkKeyboard() {
    }

    public VkKeyboard(Keyboard wrappingKeyboard) {
        this.buttons = wrappingKeyboard.getButtons().stream()
                .map(listButtons -> listButtons.stream()
                        .map(button -> new VkButton(button).as(Button.class))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        this.keyboardType = Optional.ofNullable(wrappingKeyboard.getInline()).orElse(false) ? KeyboardType.inline() : KeyboardType.reply();
    }

    public Keyboard create() {
        Keyboard keyboard = new Keyboard(buttons.stream()
                .map(listButtons -> listButtons.stream()
                        .map(button -> button.as(VkButton.class).create())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList()));
        keyboard.setInline(keyboardType.isInline());
        return keyboard;
    }

    public static class Builder extends DefaultKeyboardBuilder {

        public Builder() {
            super(new VkKeyboard());
        }

    }

}
