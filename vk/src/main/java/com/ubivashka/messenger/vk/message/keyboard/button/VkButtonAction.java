package com.ubivashka.messenger.vk.message.keyboard.button;

import com.ubivaska.messenger.common.button.ButtonAction;
import com.vk.api.sdk.objects.messages.TemplateActionTypeNames;

public class VkButtonAction implements ButtonAction {
	private final TemplateActionTypeNames buttonActionType;

	public VkButtonAction(TemplateActionTypeNames buttonActionType) {
		this.buttonActionType = buttonActionType;
	}

	public TemplateActionTypeNames getButtonActionType() {
		return buttonActionType;
	}

	public static class VkButtonActionBuilder implements ButtonActionBuilder {
		@Override
		public ButtonAction reply() {
			return new VkButtonAction(TemplateActionTypeNames.TEXT);
		}

		@Override
		public ButtonAction callback() {
			return new VkButtonAction(TemplateActionTypeNames.CALLBACK);
		}

		@Override
		public ButtonAction link() {
			return new VkButtonAction(TemplateActionTypeNames.OPEN_LINK);
		}
	}
}
