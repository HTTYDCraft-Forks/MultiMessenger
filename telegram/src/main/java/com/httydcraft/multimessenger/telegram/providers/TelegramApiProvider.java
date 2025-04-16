package com.httydcraft.multimessenger.telegram.providers;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.ApiProvider;
import com.pengrad.telegrambot.TelegramBot;

/**
 * Telegram API provider interface for TelegramBot integration.
 */
//region TelegramApiProvider Interface
public interface TelegramApiProvider extends ApiProvider {
    GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Gets the TelegramBot instance.
     * @return TelegramBot instance
     */
    TelegramBot getBot();

    /**
     * Creates a TelegramApiProvider from a TelegramBot instance.
     * @param telegramBot TelegramBot instance
     * @return TelegramApiProvider instance
     */
    static TelegramApiProvider of(TelegramBot telegramBot) {
        logger.atFine().log("of(TelegramBot) called with bot: %s", telegramBot);
        return new DefaultTelegramApiProvider(telegramBot);
    }
}
//endregion
