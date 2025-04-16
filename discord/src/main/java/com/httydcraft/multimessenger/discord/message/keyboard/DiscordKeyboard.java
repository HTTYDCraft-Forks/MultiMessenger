package com.httydcraft.multimessenger.discord.message.keyboard;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import java.util.Collection;
import java.util.stream.Collectors;

import com.httydcraft.multimessenger.core.button.Button;
import com.httydcraft.multimessenger.core.keyboard.DefaultKeyboard;
import com.httydcraft.multimessenger.discord.message.keyboard.button.DiscordButton;

import net.dv8tion.jda.api.interactions.components.ActionRow;

/**
 * Discord implementation of the Keyboard interface.
 */
//region DiscordKeyboard Class
public class DiscordKeyboard extends DefaultKeyboard {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Constructs an empty DiscordKeyboard.
     */
    public DiscordKeyboard() {
        logger.atFine().log("DiscordKeyboard created (empty)");
    }

    /**
     * Constructs a DiscordKeyboard from action rows.
     * @param actionRows action rows to use
     */
    public DiscordKeyboard(Collection<ActionRow> actionRows) {
        Preconditions.checkNotNull(actionRows, "ActionRows cannot be null");
        this.buttons = actionRows.stream()
                .map(ActionRow::getButtons)
                .map(nativeButtons -> nativeButtons.stream()
                        .map(DiscordButton::new)
                        .map(discordButton -> (Button) discordButton)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        logger.atFine().log("DiscordKeyboard created from actionRows");
    }

    /**
     * Creates a collection of ActionRows from this keyboard.
     * @return collection of ActionRows
     */
    public Collection<ActionRow> create() {
        logger.atFine().log("create() called");
        return buttons.stream()
                .map(buttonList -> buttonList.stream().map(button -> button.as(DiscordButton.class).create()).collect(Collectors.toList()))
                .map(ActionRow::of)
                .collect(Collectors.toList());
    }

    /**
     * Builder for DiscordKeyboard.
     */
    //region Builder Class
    public static class Builder extends DefaultKeyboardBuilder {
        /**
         * Constructs a Builder for DiscordKeyboard.
         */
        public Builder() {
            super(new DiscordKeyboard());
            logger.atFine().log("DiscordKeyboard.Builder created");
        }
    }
    //endregion
}
//endregion
