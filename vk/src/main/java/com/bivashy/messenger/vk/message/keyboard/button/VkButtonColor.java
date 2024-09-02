package com.bivashy.messenger.vk.message.keyboard.button;

import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.Button.Color;
import com.bivashy.messenger.common.button.ButtonColor;

public class VkButtonColor implements ButtonColor {

    private final Button.Color buttonColor;

    public VkButtonColor(Button.Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    @Override
    public String asJsonValue() {
        return buttonColor.name().toLowerCase();
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public static class Builder implements ButtonColorBuilder {

        @Override
        public ButtonColor red() {
            return new VkButtonColor(Button.Color.NEGATIVE);
        }

        @Override
        public ButtonColor blue() {
            return new VkButtonColor(Button.Color.PRIMARY);
        }

        @Override
        public ButtonColor green() {
            return new VkButtonColor(Button.Color.POSITIVE);
        }

        @Override
        public ButtonColor white() {
            return new VkButtonColor(Color.SECONDARY);
        }

        @Override
        public ButtonColor grey() {
            return new VkButtonColor(Color.SECONDARY);
        }

    }

}
