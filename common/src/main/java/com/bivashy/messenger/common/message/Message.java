package com.bivashy.messenger.common.message;

import com.bivashy.messenger.common.function.Castable;
import com.bivashy.messenger.common.identificator.Identificator;
import com.bivashy.messenger.common.keyboard.Keyboard;
import com.bivashy.messenger.common.ApiProvider;
import com.bivashy.messenger.common.file.MessengerFile;

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
