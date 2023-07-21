package com.bivashy.messenger.discord.message.keyboard.button;

import com.bivashy.messenger.common.button.ButtonColor;

import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

public class DiscordButtonColor implements ButtonColor {
    private final ButtonStyle style;

    public DiscordButtonColor(ButtonStyle style) {
        this.style = style;
    }

    public ButtonStyle getStyle() {
        return style;
    }

    @Override
    public String asJsonValue() {
        return Integer.toString(style.getKey());
    }

    public Button create(String componentId, String label) {
        return Button.of(style, componentId, label);
    }

    public static class Builder implements ButtonColorBuilder {
        @Override
        public ButtonColor red() {
            return new DiscordButtonColor(ButtonStyle.DANGER);
        }

        @Override
        public ButtonColor blue() {
            return new DiscordButtonColor(ButtonStyle.PRIMARY);
        }

        @Override
        public ButtonColor green() {
            return new DiscordButtonColor(ButtonStyle.SUCCESS);
        }

        @Override
        public ButtonColor white() {
            return new DiscordButtonColor(ButtonStyle.SECONDARY);
        }

        @Override
        public ButtonColor grey() {
            return new DiscordButtonColor(ButtonStyle.SECONDARY);
        }

        public ButtonColor link() {
            return new DiscordButtonColor(ButtonStyle.LINK);
        }
    }
}
