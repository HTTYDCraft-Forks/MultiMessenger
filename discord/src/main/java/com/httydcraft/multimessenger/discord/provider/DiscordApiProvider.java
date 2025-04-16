package com.httydcraft.multimessenger.discord.provider;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.ApiProvider;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * Discord API provider interface for JDA integration.
 */
//region DiscordApiProvider Interface
public interface DiscordApiProvider extends ApiProvider {
    GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Creates a DiscordApiProvider from a bot token.
     * @param token bot token
     * @return DiscordApiProvider instance
     */
    static DiscordApiProvider of(String token) {
        logger.atFine().log("of(String) called with token");
        return of(JDABuilder.createDefault(token).build());
    }

    /**
     * Creates a DiscordApiProvider from a JDA instance.
     * @param jda JDA instance
     * @return DiscordApiProvider instance
     */
    static DiscordApiProvider of(JDA jda) {
        logger.atFine().log("of(JDA) called with JDA instance");
        return new DefaultDiscordApiProvider(jda);
    }

    /**
     * Gets the JDA instance.
     * @return JDA instance
     */
    JDA getJDA();
}
//endregion
