package com.httydcraft.multimessenger.core.button;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.function.Castable;

/**
 * Interface for button colors in the messenger UI.
 */
//region ButtonColor Interface
public interface ButtonColor extends Castable<ButtonColor> {
    /**
     * Gets the button color as a JSON value.
     * @return color as JSON string
     */
    String asJsonValue();

    /**
     * Builder for ButtonColor implementations.
     */
    //region ButtonColorBuilder Interface
    interface ButtonColorBuilder {
        /**
         * Returns a builder that does not support any colors (all methods return empty colors).
         * @return unsupported ButtonColorBuilder
         */
        static ButtonColorBuilder unsupportedBuilder() {
            GoogleLogger logger = GoogleLogger.forEnclosingClass();
            logger.atFine().log("ButtonColorBuilder.unsupportedBuilder() called");
            return new ButtonColorBuilder() {
                @Override
                public ButtonColor white() {
                    logger.atFine().log("white() called on unsupportedBuilder");
                    return empty();
                }

                @Override
                public ButtonColor red() {
                    logger.atFine().log("red() called on unsupportedBuilder");
                    return empty();
                }

                @Override
                public ButtonColor grey() {
                    logger.atFine().log("grey() called on unsupportedBuilder");
                    return empty();
                }

                @Override
                public ButtonColor green() {
                    logger.atFine().log("green() called on unsupportedBuilder");
                    return empty();
                }

                @Override
                public ButtonColor blue() {
                    logger.atFine().log("blue() called on unsupportedBuilder");
                    return empty();
                }

                private ButtonColor empty() {
                    logger.atFine().log("empty() called on unsupportedBuilder");
                    return () -> null;
                }
            };
        }

        /**
         * Gets the red color.
         * @return ButtonColor
         */
        ButtonColor red();

        /**
         * Gets the blue color.
         * @return ButtonColor
         */
        ButtonColor blue();

        /**
         * Gets the green color.
         * @return ButtonColor
         */
        ButtonColor green();

        /**
         * Gets the white color.
         * @return ButtonColor
         */
        ButtonColor white();

        /**
         * Gets the grey color.
         * @return ButtonColor
         */
        ButtonColor grey();
    }
    //endregion
}
//endregion
