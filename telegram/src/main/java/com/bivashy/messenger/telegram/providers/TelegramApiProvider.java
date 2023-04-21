package com.bivashy.messenger.telegram.providers;

import com.bivashy.messenger.common.ApiProvider;
import com.pengrad.telegrambot.TelegramBot;

public interface TelegramApiProvider extends ApiProvider {
    TelegramBot getBot();

    static TelegramApiProvider of(TelegramBot telegramBot) {
        return new DefaultTelegramApiProvider(telegramBot);
    }
}
