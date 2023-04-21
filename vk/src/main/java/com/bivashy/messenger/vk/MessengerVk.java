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
import com.bivashy.messenger.vk.message.keyboard.button.VkButtonAction.VkButtonActionBuilder;
import com.bivashy.messenger.vk.message.keyboard.button.VkButtonColor.VkButtonColorBuilder;

public interface MessengerVk extends Messenger {
    MessengerVk INSTANCE = new MessengerVk() {
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

    @Override
    default String getName() {
        return "VK";
    }

    static MessengerVk getInstance() {
        return INSTANCE;
    }
}
