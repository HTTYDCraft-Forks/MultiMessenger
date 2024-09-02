package com.bivashy.messenger.vk.provider;

import api.longpoll.bots.VkBot;
import api.longpoll.bots.methods.VkBotsMethods;

public class DefaultVkApiProvider implements VkApiProvider {

    private final VkBot bot;
    private final VkBotsMethods methods;

    public DefaultVkApiProvider(VkBot bot, VkBotsMethods methods) {
        this.bot = bot;
        this.methods = methods;
    }

    public DefaultVkApiProvider(VkBot bot) {
        this.bot = bot;
        this.methods = new VkBotsMethods(bot::getAccessToken);
    }

    @Override
    public VkBot getBot() {
        return bot;
    }

    @Override
    public VkBotsMethods vk() {
        return methods;
    }

}
