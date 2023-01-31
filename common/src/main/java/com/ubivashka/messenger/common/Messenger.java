package com.ubivashka.messenger.common;

import com.ubivashka.messenger.common.button.Button.ButtonBuilder;
import com.ubivashka.messenger.common.button.ButtonAction.ButtonActionBuilder;
import com.ubivashka.messenger.common.button.ButtonColor.ButtonColorBuilder;
import com.ubivashka.messenger.common.keyboard.Keyboard.KeyboardBuilder;
import com.ubivashka.messenger.common.message.Message.MessageBuilder;

public interface Messenger {
    MessageBuilder newMessageBuilder(String text);

    ButtonBuilder newButtonBuilder(String label);

    KeyboardBuilder newKeyboardBuilder();

    ButtonActionBuilder newButtonActionBuilder();

    ButtonColorBuilder newButtonColorBuilder();

    String getName();
}
