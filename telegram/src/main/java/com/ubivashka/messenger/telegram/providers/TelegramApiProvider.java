package com.ubivashka.messenger.telegram.providers;

import com.pengrad.telegrambot.TelegramBot;
import com.ubivashka.messenger.common.ApiProvider;

public interface TelegramApiProvider extends ApiProvider {
    TelegramBot getBot();

    static TelegramApiProvider of(TelegramBot telegramBot) {
        return new DefaultTelegramApiProvider(telegramBot);
    }
}
