package com.httydcraft.multimessenger.core.button;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.function.Castable;

/**
 * Interface for button actions in the messenger UI.
 */
//region ButtonAction Interface
public interface ButtonAction extends Castable<ButtonAction> {
    /**
     * Builder for ButtonAction implementations.
     */
    //region ButtonActionBuilder Interface
    interface ButtonActionBuilder {
        /**
         * Returns a builder that does not support any actions (all methods return empty actions).
         * @return unsupported ButtonActionBuilder
         */
        static ButtonActionBuilder unsupportedBuilder() {
            GoogleLogger logger = GoogleLogger.forEnclosingClass();
            logger.atFine().log("unsupportedBuilder() called");
            return new ButtonActionBuilder() {
                @Override
                public ButtonAction callback() {
                    logger.atFine().log("callback() called on unsupportedBuilder");
                    return empty();
                }

                @Override
                public ButtonAction link() {
                    logger.atFine().log("link() called on unsupportedBuilder");
                    return empty();
                }

                @Override
                public ButtonAction reply() {
                    logger.atFine().log("reply() called on unsupportedBuilder");
                    return empty();
                }

                private ButtonAction empty() {
                    logger.atFine().log("empty() called on unsupportedBuilder");
                    return new ButtonAction() {
                    };
                }
            };
        }

        /**
         * Creates a callback action.
         * @return ButtonAction
         */
        ButtonAction callback();

        /**
         * Creates a link action.
         * @return ButtonAction
         */
        ButtonAction link();

        /**
         * Creates a reply action.
         * @return ButtonAction
         */
        ButtonAction reply();
    }
    //endregion
}
//endregion
