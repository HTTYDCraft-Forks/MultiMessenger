package com.ubivashka.messenger.common.message;

import com.ubivashka.functions.Castable;
import com.ubivashka.messenger.common.ApiProvider;
import com.ubivashka.messenger.common.file.MessengerFile;
import com.ubivashka.messenger.common.identificator.Identificator;
import com.ubivashka.messenger.common.keyboard.Keyboard;

public interface Message extends Castable<Message> {
    /**
     * Returns only message text.
     *
     * @return message text.
     */
    String getText();

    Keyboard getKeyboard();

    Message attachFiles(MessengerFile... files);

    Message reply(Identificator messageIdentificator);

    void send(Identificator identificator);

    void send(Identificator identificator, ApiProvider apiProvider);

    interface MessageBuilder {
        MessageBuilder keyboard(Keyboard keyboard);

        MessageBuilder text(String text);

        MessageBuilder reply(Identificator messageIdentificator);

        MessageBuilder attachFiles(MessengerFile... files);

        Message build();
    }
}
