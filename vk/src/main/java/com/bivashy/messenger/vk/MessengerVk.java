package com.bivashy.messenger.vk;

import com.bivashy.messenger.common.Messenger;
import com.bivashy.messenger.common.button.Button.ButtonBuilder;
import com.bivashy.messenger.common.button.ButtonAction.ButtonActionBuilder;
import com.bivashy.messenger.common.button.ButtonColor.ButtonColorBuilder;
import com.bivashy.messenger.common.keyboard.Keyboard.KeyboardBuilder;
import com.bivashy.messenger.common.message.Message.MessageBuilder;
import com.bivashy.messenger.vk.message.VkMessage;
import com.bivashy.messenger.vk.message.keyboard.VkKeyboard;
import com.bivashy.messenger.vk.message.keyboard.button.VkButton;
import com.bivashy.messenger.vk.message.keyboard.button.VkButtonAction;
import com.bivashy.messenger.vk.message.keyboard.button.VkButtonColor;

public interface MessengerVk extends Messenger {
    MessengerVk INSTANCE = new MessengerVk() {
    };

    static MessengerVk getInstance() {
        return INSTANCE;
    }

    @Override
    default MessageBuilder newMessageBuilder(String text) {
        return new VkMessage.Builder(text);
    }

    @Override
    default ButtonBuilder newButtonBuilder(String label) {
        return new VkButton.Builder(label);
    }

    @Override
    default KeyboardBuilder newKeyboardBuilder() {
        return new VkKeyboard.Builder();
    }

    @Override
    default ButtonActionBuilder newButtonActionBuilder() {
        return new VkButtonAction.Builder();
    }

    @Override
    default ButtonColorBuilder newButtonColorBuilder() {
        return new VkButtonColor.Builder();
    }

    @Override
    default String getName() {
        return "VK";
    }
}
