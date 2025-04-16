package com.httydcraft.multimessenger.vk.message.keyboard.button;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.button.ButtonAction;
import com.vk.api.sdk.objects.messages.TemplateActionTypeNames;

/**
 * VK implementation of a button action.
 */
//region VkButtonAction Class
public class VkButtonAction implements ButtonAction {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private final TemplateActionTypeNames buttonActionType;

    /**
     * Constructs a VkButtonAction with the given action type.
     * @param buttonActionType VK button action type
     */
    public VkButtonAction(TemplateActionTypeNames buttonActionType) {
        this.buttonActionType = buttonActionType;
        logger.atFine().log("VkButtonAction created with type: %s", buttonActionType);
    }

    /**
     * Gets the VK button action type.
     * @return VK button action type
     */
    public TemplateActionTypeNames getButtonActionType() {
        return buttonActionType;
    }

    /**
     * Builder for VkButtonAction.
     */
    public static class Builder implements ButtonActionBuilder {
        @Override
        public ButtonAction reply() {
            logger.atFine().log("ButtonActionBuilder.reply() called");
            return new VkButtonAction(TemplateActionTypeNames.TEXT);
        }

        @Override
        public ButtonAction callback() {
            logger.atFine().log("ButtonActionBuilder.callback() called");
            return new VkButtonAction(TemplateActionTypeNames.CALLBACK);
        }

        @Override
        public ButtonAction link() {
            logger.atFine().log("ButtonActionBuilder.link() called");
            return new VkButtonAction(TemplateActionTypeNames.OPEN_LINK);
        }
    }
}
//endregion
