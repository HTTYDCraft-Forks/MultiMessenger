package com.httydcraft.multimessenger.vk.provider;

import com.httydcraft.multimessenger.core.ApiProvider;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

public interface VkApiProvider extends ApiProvider {
    GroupActor getActor();

    VkApiClient getClient();

    static VkApiProvider of(GroupActor actor, VkApiClient client) {
        return new DefaultVkApiProvider(actor, client);
    }
}
