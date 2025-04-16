package com.httydcraft.multimessenger.discord.message.keyboard.button;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.button.ButtonColor;

import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

/**
 * Discord implementation of the ButtonColor interface.
 */
//region DiscordButtonColor Class
public class DiscordButtonColor implements ButtonColor {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private final ButtonStyle style;

    /**
     * Constructs a DiscordButtonColor with the given ButtonStyle.
     * @param style button style
     */
    public DiscordButtonColor(ButtonStyle style) {
        this.style = style;
        logger.atFine().log("DiscordButtonColor created with style: %s", style);
    }

    /**
     * Gets the ButtonStyle.
     * @return button style
     */
    public ButtonStyle getStyle() {
        logger.atFine().log("getStyle() called, returning: %s", style);
        return style;
    }

    /**
     * Gets the color as a JSON value.
     * @return color as JSON string
     */
    @Override
    public String asJsonValue() {
        logger.atFine().log("asJsonValue() called, returning: %d", style.getKey());
        return Integer.toString(style.getKey());
    }

    /**
     * Creates a JDA Button with this color.
     * @param componentId component id
     * @param label button label
     * @return JDA Button
     */
    public Button create(String componentId, String label) {
        logger.atFine().log("create() called with id: %s, label: %s", componentId, label);
        return Button.of(style, componentId, label);
    }

    /**
     * Builder for DiscordButtonColor.
     */
    //region Builder Class
    public static class Builder implements ButtonColorBuilder {
        @Override
        public ButtonColor red() {
            logger.atFine().log("Builder.red() called");
            return new DiscordButtonColor(ButtonStyle.DANGER);
        }

        @Override
        public ButtonColor blue() {
            logger.atFine().log("Builder.blue() called");
            return new DiscordButtonColor(ButtonStyle.PRIMARY);
        }

        @Override
        public ButtonColor green() {
            logger.atFine().log("Builder.green() called");
            return new DiscordButtonColor(ButtonStyle.SUCCESS);
        }

        @Override
        public ButtonColor white() {
            logger.atFine().log("Builder.white() called");
            return new DiscordButtonColor(ButtonStyle.SECONDARY);
        }

        @Override
        public ButtonColor grey() {
            logger.atFine().log("Builder.grey() called");
            return new DiscordButtonColor(ButtonStyle.SECONDARY);
        }

        public ButtonColor link() {
            logger.atFine().log("Builder.link() called");
            return new DiscordButtonColor(ButtonStyle.LINK);
        }
    }
    //endregion
}
//endregion
