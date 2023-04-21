package com.bivashy.messenger.common;

import com.bivashy.messenger.common.button.Button.ButtonBuilder;
import com.bivashy.messenger.common.button.ButtonAction.ButtonActionBuilder;
import com.bivashy.messenger.common.button.ButtonColor.ButtonColorBuilder;
import com.bivashy.messenger.common.keyboard.Keyboard.KeyboardBuilder;
import com.bivashy.messenger.common.message.Message.MessageBuilder;

public interface Messenger {
    MessageBuilder newMessageBuilder(String text);

    ButtonBuilder newButtonBuilder(String label);

    KeyboardBuilder newKeyboardBuilder();

    ButtonActionBuilder newButtonActionBuilder();

    ButtonColorBuilder newButtonColorBuilder();

    String getName();
}
