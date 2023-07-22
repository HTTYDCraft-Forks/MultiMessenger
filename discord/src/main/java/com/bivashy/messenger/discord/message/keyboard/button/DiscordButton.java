package com.bivashy.messenger.discord.message.keyboard.button;

import com.bivashy.messenger.common.button.DefaultButton;

import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class DiscordButton extends DefaultButton {
    public DiscordButton(String label) {
        super(label);
    }

    public DiscordButton(Button button) {
        super(button.getLabel());
        this.color = new DiscordButtonColor(button.getStyle());
        this.actionData = button.getId();
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
