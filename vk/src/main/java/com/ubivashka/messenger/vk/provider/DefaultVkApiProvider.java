package com.ubivashka.messenger.vk.provider;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

public class DefaultVkApiProvider implements VkApiProvider {
    private final GroupActor actor;
    private final VkApiClient client;

    public DefaultVkApiProvider(GroupActor actor, VkApiClient client) {
        this.actor = actor;
        this.client = client;
    }

    public GroupActor getActor() {
        return actor;
    }

    public VkApiClient getClient() {
        return client;
    }
}
