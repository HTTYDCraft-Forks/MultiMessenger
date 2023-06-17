package com.bivashy.messenger.discord.message.keyboard;

import java.util.Collection;
import java.util.stream.Collectors;

import com.bivashy.messenger.common.keyboard.DefaultKeyboard;
import com.bivashy.messenger.discord.message.keyboard.button.DiscordButton;

import net.dv8tion.jda.api.interactions.components.ActionRow;

public class DiscordKeyboard extends DefaultKeyboard {
    private Collection<ActionRow> actionRows;

    public DiscordKeyboard() {
    }

    public DiscordKeyboard(Collection<ActionRow> actionRows) {
        this.actionRows = actionRows;
    }

    public Collection<ActionRow> create() {
        if (actionRows != null)
            return actionRows;
        return buttons.stream()
                .map(buttonList -> buttonList.stream().map(button -> button.as(DiscordButton.class).create()).collect(Collectors.toList()))
                .map(ActionRow::of)
                .collect(Collectors.toList());
    }

    public static class Builder extends DefaultKeyboardBuilder {
        public Builder() {
            super(new DiscordKeyboard());
        }
    }
}
