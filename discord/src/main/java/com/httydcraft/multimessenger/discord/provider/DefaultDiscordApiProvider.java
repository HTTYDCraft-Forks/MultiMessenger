package com.httydcraft.multimessenger.discord.provider;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import net.dv8tion.jda.api.JDA;

/**
 * Default implementation of the DiscordApiProvider interface.
 */
//region DefaultDiscordApiProvider Class
public class DefaultDiscordApiProvider implements DiscordApiProvider {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private final JDA jda;

    /**
     * Constructs a DefaultDiscordApiProvider with the given JDA instance.
     * @param jda JDA instance
     */
    public DefaultDiscordApiProvider(JDA jda) {
        Preconditions.checkNotNull(jda, "JDA cannot be null");
        this.jda = jda;
        logger.atFine().log("DefaultDiscordApiProvider created with JDA: %s", jda);
    }

    /**
     * Gets the JDA instance.
     * @return JDA instance
     */
    @Override
    public JDA getJDA() {
        logger.atFine().log("getJDA() called, returning: %s", jda);
        return jda;
    }
}
//endregion
