package com.bivashy.messenger.telegram.message.keyboard;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.bivashy.messenger.common.button.Button;
import com.bivashy.messenger.common.keyboard.DefaultKeyboard;
import com.bivashy.messenger.telegram.message.keyboard.button.TelegramButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class TelegramKeyboard extends DefaultKeyboard {
    public TelegramKeyboard() {
    }

    public TelegramKeyboard(InlineKeyboardMarkup wrappingKeyboard) {
        this.buttons = Arrays.stream(wrappingKeyboard.inlineKeyboard())
                .map(buttonsArray -> Arrays.stream(buttonsArray)
                        .map(button -> new TelegramButton(button).as(Button.class)).collect(Collectors.toList()))
                .collect(Collectors.toList());
        this.keyboardType = KeyboardType.inline();
    }

    public com.pengrad.telegrambot.model.request.Keyboard create() {
        if (keyboardType.isInline()) {
            return new InlineKeyboardMarkup(buttons.stream()
                    .map(buttonList -> buttonList.stream()
                            .map(button -> button.as(TelegramButton.class).createInlineButton())
                            .toArray(InlineKeyboardButton[]::new))
                    .toArray(InlineKeyboardButton[][]::new));
        }
        if (keyboardType.isReply()) {
            return new ReplyKeyboardMarkup(buttons.stream()
                    .map(buttonList -> buttonList.stream()
                            .map(button -> button.as(TelegramButton.class).createReplyButton())
                            .toArray(KeyboardButton[]::new))
                    .toArray(KeyboardButton[][]::new));
        }
        return null;
    }

    public class TelegramKeyboardBuilder extends DefaultKeyboardBuilder {
        public TelegramKeyboardBuilder() {
            super(TelegramKeyboard.this);
        }
    }
}
