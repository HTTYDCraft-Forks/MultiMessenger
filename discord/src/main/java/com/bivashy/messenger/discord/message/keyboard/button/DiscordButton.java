package com.bivashy.messenger.discord.message.keyboard.button;

import com.bivashy.messenger.common.button.DefaultButton;

import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class DiscordButton extends DefaultButton {
    public DiscordButton(String label) {
        super(label);
    }

    public Button create() {
        return color.as(DiscordButtonColor.class).create(actionData, label);
    }

    public static class Builder extends DefaultButtonBuilder {
        public Builder(String label) {
            super(new DiscordButton(label));
        }
    }
}
