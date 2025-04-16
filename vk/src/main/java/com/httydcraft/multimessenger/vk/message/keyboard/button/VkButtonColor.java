package com.httydcraft.multimessenger.vk.message.keyboard.button;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.button.ButtonColor;
import com.vk.api.sdk.objects.messages.KeyboardButtonColor;

/**
 * VK implementation of a button color.
 */
//region VkButtonColor Class
public class VkButtonColor implements ButtonColor {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private final KeyboardButtonColor buttonColor;

    /**
     * Constructs a VkButtonColor with the given VK button color.
     * @param buttonColor VK button color
     */
    public VkButtonColor(KeyboardButtonColor buttonColor) {
        this.buttonColor = buttonColor;
        logger.atFine().log("VkButtonColor created with color: %s", buttonColor);
    }

    /**
     * Gets the JSON value of the button color.
     * @return JSON value
     */
    @Override
    public String asJsonValue() {
        return buttonColor.name().toLowerCase();
    }

    /**
     * Gets the VK button color.
     * @return VK button color
     */
    public KeyboardButtonColor getButtonColor() {
        return buttonColor;
    }

    /**
     * Builder for VkButtonColor.
     */
    public static class Builder implements ButtonColorBuilder {
        @Override
        public ButtonColor red() {
            logger.atFine().log("ButtonColorBuilder.red() called");
            return new VkButtonColor(KeyboardButtonColor.NEGATIVE);
        }

        @Override
        public ButtonColor blue() {
            logger.atFine().log("ButtonColorBuilder.blue() called");
            return new VkButtonColor(KeyboardButtonColor.PRIMARY);
        }

        @Override
        public ButtonColor green() {
            logger.atFine().log("ButtonColorBuilder.green() called");
            return new VkButtonColor(KeyboardButtonColor.POSITIVE);
        }

        @Override
        public ButtonColor white() {
            logger.atFine().log("ButtonColorBuilder.white() called");
            return new VkButtonColor(KeyboardButtonColor.DEFAULT);
        }

        @Override
        public ButtonColor grey() {
            logger.atFine().log("ButtonColorBuilder.grey() called");
            return new VkButtonColor(KeyboardButtonColor.DEFAULT);
        }
    }
}
//endregion
