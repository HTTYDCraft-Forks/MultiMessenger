package com.bivashy.messenger.telegram;

import com.bivashy.messenger.common.Messenger;
import com.bivashy.messenger.common.button.Button.ButtonBuilder;
import com.bivashy.messenger.common.button.ButtonAction.ButtonActionBuilder;
import com.bivashy.messenger.common.button.ButtonColor.ButtonColorBuilder;
import com.bivashy.messenger.common.keyboard.Keyboard.KeyboardBuilder;
import com.bivashy.messenger.common.message.Message.MessageBuilder;
import com.bivashy.messenger.telegram.message.TelegramMessage;
import com.bivashy.messenger.telegram.message.keyboard.TelegramKeyboard;
import com.bivashy.messenger.telegram.message.keyboard.button.TelegramButton;
import com.bivashy.messenger.telegram.message.keyboard.button.TelegramButtonAction;

public interface MessengerTelegram extends Messenger {
    MessengerTelegram INSTANCE = new MessengerTelegram() {
    };

    static MessengerTelegram getInstance() {
        return INSTANCE;
    }

    @Override
    default MessageBuilder newMessageBuilder(String text) {
        return new TelegramMessage.Builder(text);
    }

    @Override
    default ButtonBuilder newButtonBuilder(String label) {
        return new TelegramButton.Builder(label);
    }

    @Override
    default KeyboardBuilder newKeyboardBuilder() {
        return new TelegramKeyboard.Builder();
    }

    @Override
    default ButtonActionBuilder newButtonActionBuilder() {
        return new TelegramButtonAction.Builder();
    }

    @Override
    default ButtonColorBuilder newButtonColorBuilder() {
        return ButtonColorBuilder.unsupportedBuilder();
    }

    @Override
    default String getName() {
        return "TELEGRAM";
    }
}
