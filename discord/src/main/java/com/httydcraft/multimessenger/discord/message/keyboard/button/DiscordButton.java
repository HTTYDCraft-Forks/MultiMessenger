package com.httydcraft.multimessenger.discord.message.keyboard.button;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.button.DefaultButton;

import net.dv8tion.jda.api.interactions.components.buttons.Button;

/**
 * Discord implementation of the DefaultButton.
 */
//region DiscordButton Class
public class DiscordButton extends DefaultButton {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Constructs a DiscordButton with the given label.
     * @param label button label
     */
    public DiscordButton(String label) {
        super(label);
        logger.atFine().log("DiscordButton created with label: %s", label);
    }

    /**
     * Constructs a DiscordButton from a JDA Button.
     * @param button JDA button
     */
    public DiscordButton(Button button) {
        super(button.getLabel());
        Preconditions.checkNotNull(button, "Button cannot be null");
        this.color = new DiscordButtonColor(button.getStyle());
        this.actionData = button.getId();
        logger.atFine().log("DiscordButton created from JDA Button: %s", button);
    }

    /**
     * Creates a JDA Button from this DiscordButton.
     * @return JDA Button
     */
    public Button create() {
        logger.atFine().log("create() called for DiscordButton");
        return color.as(DiscordButtonColor.class).create(actionData, label);
    }

    /**
     * Builder for DiscordButton.
     */
    //region Builder Class
    public static class Builder extends DefaultButtonBuilder {
        /**
         * Constructs a Builder for DiscordButton.
         * @param label button label
         */
        public Builder(String label) {
            super(new DiscordButton(label));
            logger.atFine().log("DiscordButton.Builder created with label: %s", label);
        }
    }
    //endregion
}
//endregion
