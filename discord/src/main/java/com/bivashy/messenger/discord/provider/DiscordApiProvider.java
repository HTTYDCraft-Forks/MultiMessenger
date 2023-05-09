package com.bivashy.messenger.discord.provider;

import com.bivashy.messenger.common.ApiProvider;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public interface DiscordApiProvider extends ApiProvider {
    static DiscordApiProvider of(String token) {
        return of(JDABuilder.createDefault(token).build());
    }

    static DiscordApiProvider of(JDA jda) {
        return new DefaultDiscordApiProvider(jda);
    }

    JDA getJDA();
}
