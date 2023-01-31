package com.ubivashka.messenger.vk.provider;

import com.ubivashka.messenger.common.ApiProvider;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

public interface VkApiProvider extends ApiProvider {
	GroupActor getActor();

	VkApiClient getClient();

	static VkApiProvider of(GroupActor actor, VkApiClient client) {
		return new DefaultVkApiProvider(actor, client);
	}
}
