package com.bivashy.messenger.vk.message.keyboard.button;

import com.bivashy.messenger.common.button.DefaultButton;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.KeyboardButtonAction;
import com.vk.api.sdk.objects.messages.KeyboardButtonColor;
import com.vk.api.sdk.objects.messages.TemplateActionTypeNames;

public class VkButton extends DefaultButton {
    private static final VkButtonAction DEFAULT_ACTION = new VkButtonAction(TemplateActionTypeNames.TEXT);
    private static final VkButtonColor DEFAULT_COLOR = new VkButtonColor(KeyboardButtonColor.DEFAULT);

    public VkButton(KeyboardButton wrappingButton) {
        super(wrappingButton.getAction().getLabel());
        this.action = new VkButtonAction(wrappingButton.getAction().getType());
        this.color = new VkButtonColor(wrappingButton.getColor());
        switch (wrappingButton.getAction().getType()) {
            case CALLBACK:
                this.actionData = wrappingButton.getAction().getPayload();
                break;
            case OPEN_LINK:
                this.actionData = wrappingButton.getAction().getLink();
                break;
            default:
                break;
        }
    }

    public VkButton(String label) {
        super(label);
    }

    public KeyboardButton create() {
        KeyboardButton keyboardButton = new KeyboardButton();
        KeyboardButtonAction buttonAction = new KeyboardButtonAction();
        TemplateActionTypeNames buttonType = action.safeAs(VkButtonAction.class, DEFAULT_ACTION).getButtonActionType();
        buttonAction.setType(buttonType);
        if (buttonType == TemplateActionTypeNames.CALLBACK)
            buttonAction.setPayload(actionData);
        if (buttonType == TemplateActionTypeNames.OPEN_LINK)
            buttonAction.setLink(actionData);
        buttonAction.setLabel(label);
        keyboardButton.setAction(buttonAction);
        if (color != null)
            keyboardButton.setColor(color.safeAs(VkButtonColor.class, DEFAULT_COLOR).getButtonColor());
        return keyboardButton;
    }

    public class VkButtonBuilder extends DefaultButtonBuilder {
        public VkButtonBuilder() {
            super(VkButton.this);
        }
    }
}
