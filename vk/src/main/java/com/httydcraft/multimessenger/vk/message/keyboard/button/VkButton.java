package com.httydcraft.multimessenger.vk.message.keyboard.button;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.button.DefaultButton;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.KeyboardButtonAction;
import com.vk.api.sdk.objects.messages.KeyboardButtonColor;
import com.vk.api.sdk.objects.messages.TemplateActionTypeNames;

/**
 * VK implementation of a keyboard button.
 */
//region VkButton Class
public class VkButton extends DefaultButton {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private static final VkButtonAction DEFAULT_ACTION = new VkButtonAction(TemplateActionTypeNames.TEXT);
    private static final VkButtonColor DEFAULT_COLOR = new VkButtonColor(KeyboardButtonColor.DEFAULT);

    /**
     * Constructs a VkButton from a VK API button.
     * @param wrappingButton VK API button
     */
    public VkButton(KeyboardButton wrappingButton) {
        super(wrappingButton.getAction().getLabel());
        Preconditions.checkNotNull(wrappingButton, "KeyboardButton cannot be null");
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
        logger.atFine().log("VkButton created from VK API button: %s", wrappingButton);
    }

    /**
     * Constructs a VkButton with the given label.
     * @param label button label
     */
    public VkButton(String label) {
        super(label);
        Preconditions.checkNotNull(label, "Button label cannot be null");
        logger.atFine().log("VkButton created with label: %s", label);
    }

    /**
     * Creates a VK API KeyboardButton from this VkButton.
     * @return VK API KeyboardButton
     */
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
        logger.atFine().log("VK API KeyboardButton created from VkButton: %s", label);
        return keyboardButton;
    }

    /**
     * Builder for VkButton.
     */
    public static class Builder extends DefaultButtonBuilder {
        public Builder(String label) {
            super(new VkButton(label));
        }
    }
}
//endregion
