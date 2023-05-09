package com.bivashy.messenger.discord;

import com.bivashy.messenger.common.Messenger;
import com.bivashy.messenger.common.button.Button.ButtonBuilder;
import com.bivashy.messenger.common.button.ButtonAction.ButtonActionBuilder;
import com.bivashy.messenger.common.button.ButtonColor.ButtonColorBuilder;
import com.bivashy.messenger.common.keyboard.Keyboard.KeyboardBuilder;
import com.bivashy.messenger.common.message.Message.MessageBuilder;
import com.bivashy.messenger.discord.message.DiscordMessage;
import com.bivashy.messenger.discord.message.keyboard.DiscordKeyboard;
import com.bivashy.messenger.discord.message.keyboard.button.DiscordButton;
import com.bivashy.messenger.discord.message.keyboard.button.DiscordButtonColor;

public interface MessengerDiscord extends Messenger {
    MessengerDiscord INSTANCE = new MessengerDiscord() {
    };

    static MessengerDiscord getInstance() {
        return INSTANCE;
    }

    @Override
    default MessageBuilder newMessageBuilder(String text) {
        return new DiscordMessage.Builder(text);
    }

    @Override
    default ButtonBuilder newButtonBuilder(String label) {
        return new DiscordButton.Builder(label);
    }

    @Override
    default KeyboardBuilder newKeyboardBuilder() {
        return new DiscordKeyboard.Builder();
    }

    @Override
    default ButtonActionBuilder newButtonActionBuilder() {
        return ButtonActionBuilder.unsupportedBuilder();
    }

    @Override
    default ButtonColorBuilder newButtonColorBuilder() {
        return new DiscordButtonColor.Builder();
    }

    @Override
    default String getName() {
        return "DISCORD";
    }
}
