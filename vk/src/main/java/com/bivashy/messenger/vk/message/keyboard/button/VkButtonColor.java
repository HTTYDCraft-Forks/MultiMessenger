package com.bivashy.messenger.vk.message.keyboard.button;

import com.bivashy.messenger.common.button.ButtonColor;
import com.vk.api.sdk.objects.messages.KeyboardButtonColor;

public class VkButtonColor implements ButtonColor {
    private final KeyboardButtonColor buttonColor;

    public VkButtonColor(KeyboardButtonColor buttonColor) {
        this.buttonColor = buttonColor;
    }

    public KeyboardButtonColor getButtonColor() {
        return buttonColor;
    }

    public static class Builder implements ButtonColorBuilder {
        @Override
        public ButtonColor red() {
            return new VkButtonColor(KeyboardButtonColor.NEGATIVE);
        }

        @Override
        public ButtonColor blue() {
            return new VkButtonColor(KeyboardButtonColor.PRIMARY);
        }

        @Override
        public ButtonColor green() {
            return new VkButtonColor(KeyboardButtonColor.POSITIVE);
        }

        @Override
        public ButtonColor white() {
            return new VkButtonColor(KeyboardButtonColor.DEFAULT);
        }

        @Override
        public ButtonColor grey() {
            return new VkButtonColor(KeyboardButtonColor.DEFAULT);
        }
    }
}
