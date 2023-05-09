package com.bivashy.messenger.discord.provider;

import net.dv8tion.jda.api.JDA;

public class DefaultDiscordApiProvider implements DiscordApiProvider {
    private final JDA jda;

    public DefaultDiscordApiProvider(JDA jda) {
        this.jda = jda;
    }

    @Override
    public JDA getJDA() {
        return jda;
    }
}
