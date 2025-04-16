package com.httydcraft.multimessenger.telegram.providers;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.pengrad.telegrambot.TelegramBot;

/**
 * Default implementation of the TelegramApiProvider interface.
 */
//region DefaultTelegramApiProvider Class
public class DefaultTelegramApiProvider implements TelegramApiProvider {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private final TelegramBot bot;

    /**
     * Constructs a DefaultTelegramApiProvider with the given TelegramBot instance.
     * @param bot TelegramBot instance
     */
    public DefaultTelegramApiProvider(TelegramBot bot) {
        Preconditions.checkNotNull(bot, "TelegramBot cannot be null");
        this.bot = bot;
        logger.atFine().log("DefaultTelegramApiProvider created with TelegramBot: %s", bot);
    }

    /**
     * Gets the TelegramBot instance.
     * @return TelegramBot instance
     */
    @Override
    public TelegramBot getBot() {
        logger.atFine().log("getBot() called, returning: %s", bot);
        return bot;
    }
}
//endregion
