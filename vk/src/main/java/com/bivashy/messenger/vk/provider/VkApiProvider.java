package com.bivashy.messenger.vk.provider;

import api.longpoll.bots.VkBot;
import api.longpoll.bots.methods.VkBotsMethods;
import com.bivashy.messenger.common.ApiProvider;

public interface VkApiProvider extends ApiProvider {

    VkBot getBot();

    VkBotsMethods vk();

    static VkApiProvider of(VkBot bot) {
        return new DefaultVkApiProvider(bot);
    }

    static VkApiProvider of(VkBot bot, VkBotsMethods vk) {
        return new DefaultVkApiProvider(bot, vk);
    }

}
