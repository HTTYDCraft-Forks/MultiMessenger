package com.httydcraft.multimessenger.core.keyboard;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.httydcraft.multimessenger.core.button.Button;

/**
 * Default implementation of the Keyboard interface.
 */
//region DefaultKeyboard Class
public class DefaultKeyboard implements Keyboard {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    protected List<List<Button>> buttons = new ArrayList<>();
    protected KeyboardType keyboardType;

    /**
     * Gets a copy of the 2D list of buttons.
     * @return 2D list of buttons
     */
    @Override
    public List<List<Button>> getButtons() {
        logger.atFine().log("getButtons() called");
        return new ArrayList<>(buttons);
    }

    /**
     * Adds a row of buttons to the keyboard.
     * @param buttons buttons to add
     * @return this keyboard
     */
    @Override
    public Keyboard addRow(Button... buttons) {
        Preconditions.checkNotNull(buttons, "Buttons cannot be null");
        this.buttons.add(Arrays.asList(buttons));
        logger.atFine().log("addRow() called, added row: %s", Arrays.toString(buttons));
        return this;
    }

    /**
     * Removes buttons matching the filter.
     * @param buttonFilter predicate to filter buttons
     * @return this keyboard
     */
    @Override
    public Keyboard removeIf(Predicate<Button> buttonFilter) {
        Preconditions.checkNotNull(buttonFilter, "Button filter cannot be null");
        buttons.forEach(buttonsList -> buttonsList.removeIf(buttonFilter));
        buttons.removeIf(List::isEmpty);
        logger.atFine().log("removeIf() called");
        return this;
    }

    /**
     * Applies a function to buttons matching the filter.
     * @param filter predicate to filter buttons
     * @param function function to apply
     * @return this keyboard
     */
    @Override
    public Keyboard ifThen(Predicate<Button> filter, Function<Button, Button> function) {
        Preconditions.checkNotNull(filter, "Filter cannot be null");
        Preconditions.checkNotNull(function, "Function cannot be null");
        buttons = buttons.stream().map(buttonsList -> buttonsList.stream().map(button -> {
            if (filter.test(button))
                return function.apply(button);
            return button;
        }).collect(Collectors.toList())).collect(Collectors.toList());
        logger.atFine().log("ifThen() called");
        return this;
    }

    /**
     * Gets the keyboard type.
     * @return keyboard type
     */
    @Override
    public KeyboardType getType() {
        logger.atFine().log("getType() called, returning: %s", keyboardType);
        return keyboardType;
    }

    /**
     * Abstract builder for DefaultKeyboard.
     */
    //region DefaultKeyboardBuilder Class
    public static abstract class DefaultKeyboardBuilder implements KeyboardBuilder {
        private final DefaultKeyboard keyboard;

        /**
         * Constructs a DefaultKeyboardBuilder.
         * @param keyboard keyboard to build
         */
        public DefaultKeyboardBuilder(DefaultKeyboard keyboard) {
            Preconditions.checkNotNull(keyboard, "Keyboard cannot be null");
            this.keyboard = keyboard;
            logger.atFine().log("DefaultKeyboardBuilder created for keyboard: %s", keyboard);
        }

        /**
         * Adds a button to a specific row.
         * @param row row index
         * @param button button to add
         * @return this builder
         */
        @Override
        public KeyboardBuilder button(int row, Button button) {
            Preconditions.checkArgument(row >= 0, "Row index cannot be negative");
            Preconditions.checkNotNull(button, "Button cannot be null");
            while(keyboard.buttons.size() <= row)
                keyboard.buttons.add(new ArrayList<>());
            keyboard.buttons.get(row).add(button);
            logger.atFine().log("button() called, row: %d, button: %s", row, button);
            return this;
        }

        /**
         * Sets all buttons for the keyboard.
         * @param buttons 2d list of buttons
         * @return this builder
         */
        @Override
        public KeyboardBuilder buttons(List<List<Button>> buttons) {
            Preconditions.checkNotNull(buttons, "Buttons cannot be null");
            keyboard.buttons = buttons;
            logger.atFine().log("buttons() called, set buttons");
            return this;
        }

        /**
         * Adds a new row of buttons.
         * @param buttons buttons to add
         * @return this builder
         */
        @Override
        public KeyboardBuilder row(Button... buttons) {
            Preconditions.checkNotNull(buttons, "Buttons cannot be null");
            keyboard.addRow(buttons);
            logger.atFine().log("row() called, added row: %s", Arrays.toString(buttons));
            return this;
        }

        /**
         * Sets the keyboard type.
         * @param type keyboard type
         * @return this builder
         */
        @Override
        public KeyboardBuilder type(KeyboardType type) {
            Preconditions.checkNotNull(type, "KeyboardType cannot be null");
            keyboard.keyboardType = type;
            logger.atFine().log("type() called, set type: %s", type);
            return this;
        }

        /**
         * Builds the keyboard.
         * @return built keyboard
         */
        @Override
        public Keyboard build() {
            logger.atFine().log("build() called, returning keyboard: %s", keyboard);
            return keyboard;
        }
    }
    //endregion
}
//endregion
