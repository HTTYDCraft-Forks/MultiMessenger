package com.httydcraft.multimessenger.core.keyboard;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.function.Castable;
import com.httydcraft.multimessenger.core.button.Button;

/**
 * Interface for keyboards in the messenger UI.
 */
//region Keyboard Interface
public interface Keyboard extends Castable<Keyboard> {
    /**
     * Returns copied 2d list of buttons
     * @return 2d list of buttons
     */
    List<List<Button>> getButtons();

    /**
     * Adds a row of buttons to the keyboard.
     * @param buttons buttons to add
     * @return this keyboard
     */
    Keyboard addRow(Button... buttons);

    /**
     * Removes buttons matching the filter.
     * @param buttonFilter predicate to filter buttons
     * @return this keyboard
     */
    Keyboard removeIf(Predicate<Button> buttonFilter);

    /**
     * Applies a function to buttons matching the filter.
     * @param filter predicate to filter buttons
     * @param function function to apply
     * @return this keyboard
     */
    Keyboard ifThen(Predicate<Button> filter, Function<Button, Button> function);

    /**
     * Gets the keyboard type.
     * @return keyboard type
     */
    KeyboardType getType();

    /**
     * Interface for keyboard types (inline or reply).
     */
    //region KeyboardType Interface
    interface KeyboardType extends Castable<KeyboardType> {
        /**
         * Checks if this keyboard is inline.
         * @return true if inline
         */
        default boolean isInline() {
            return false;
        }

        /**
         * Checks if this keyboard is reply.
         * @return true if reply
         */
        default boolean isReply() {
            return false;
        }

        /**
         * Returns an inline keyboard type.
         * @return inline KeyboardType
         */
        static KeyboardType inline() {
            GoogleLogger logger = GoogleLogger.forEnclosingClass();
            logger.atFine().log("KeyboardType.inline() called");
            return new KeyboardType() {
                @Override
                public boolean isInline() {
                    return true;
                }
            };
        }

        /**
         * Returns a reply keyboard type.
         * @return reply KeyboardType
         */
        static KeyboardType reply() {
            GoogleLogger logger = GoogleLogger.forEnclosingClass();
            logger.atFine().log("KeyboardType.reply() called");
            return new KeyboardType() {
                @Override
                public boolean isReply() {
                    return true;
                }
            };
        }
    }
    //endregion

    /**
     * Builder for Keyboard implementations.
     */
    //region KeyboardBuilder Interface
    interface KeyboardBuilder {
        /**
         * Adds a button to a specific row.
         * @param row row index
         * @param button button to add
         * @return this builder
         */
        KeyboardBuilder button(int row, Button button);

        /**
         * Sets all buttons for the keyboard.
         * @param buttons 2d list of buttons
         * @return this builder
         */
        KeyboardBuilder buttons(List<List<Button>> buttons);

        /**
         * Adds a new row of buttons.
         * @param buttons buttons to add
         * @return this builder
         */
        KeyboardBuilder row(Button... buttons);

        /**
         * Sets the keyboard type.
         * @param type keyboard type
         * @return this builder
         */
        KeyboardBuilder type(KeyboardType type);

        /**
         * Builds the keyboard.
         * @return built keyboard
         */
        Keyboard build();
    }
    //endregion
}
//endregion
