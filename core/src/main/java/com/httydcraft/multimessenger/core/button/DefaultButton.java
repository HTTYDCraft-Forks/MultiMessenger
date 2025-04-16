package com.httydcraft.multimessenger.core.button;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;

/**
 * Default implementation of the Button interface.
 */
//region DefaultButton Class
public class DefaultButton implements Button {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    protected String label, actionData;
    protected ButtonColor color;
    protected ButtonAction action;

    /**
     * Constructs a DefaultButton with the given label.
     * @param label button label
     * @throws NullPointerException if label is null
     */
    public DefaultButton(String label) {
        Preconditions.checkNotNull(label, "Button label cannot be null");
        this.label = label;
        logger.atFine().log("DefaultButton created with label: %s", label);
    }

    /**
     * Gets the button label.
     * @return label
     */
    @Override
    public String getLabel() {
        logger.atFine().log("getLabel() called, returning: %s", label);
        return label;
    }

    /**
     * Gets the button action data.
     * @return action data
     */
    @Override
    public String getActionData() {
        logger.atFine().log("getActionData() called, returning: %s", actionData);
        return actionData;
    }

    /**
     * Gets the button color.
     * @return color
     */
    @Override
    public ButtonColor getColor() {
        logger.atFine().log("getColor() called, returning: %s", color);
        return color;
    }

    /**
     * Gets the button action.
     * @return action
     */
    @Override
    public ButtonAction getAction() {
        logger.atFine().log("getAction() called, returning: %s", action);
        return action;
    }

    /**
     * Abstract builder for DefaultButton.
     */
    //region DefaultButtonBuilder Class
    public static abstract class DefaultButtonBuilder implements ButtonBuilder {
        private final DefaultButton button;

        /**
         * Constructs a DefaultButtonBuilder.
         * @param button button to build
         */
        public DefaultButtonBuilder(DefaultButton button) {
            Preconditions.checkNotNull(button, "Button cannot be null");
            this.button = button;
            logger.atFine().log("DefaultButtonBuilder created for button: %s", button);
        }

        /**
         * Sets the button action.
         * @param action button action
         * @return this builder
         */
        @Override
        public ButtonBuilder action(ButtonAction action) {
            button.action = action;
            logger.atFine().log("action() called, set action: %s", action);
            return this;
        }

        /**
         * Sets the button action data.
         * @param actionData action data
         * @return this builder
         */
        @Override
        public ButtonBuilder actionData(String actionData) {
            button.actionData = actionData;
            logger.atFine().log("actionData() called, set actionData: %s", actionData);
            return this;
        }

        /**
         * Sets the button color.
         * @param color button color
         * @return this builder
         */
        @Override
        public ButtonBuilder color(ButtonColor color) {
            button.color = color;
            logger.atFine().log("color() called, set color: %s", color);
            return this;
        }

        /**
         * Sets the button label.
         * @param label button label
         * @return this builder
         */
        @Override
        public ButtonBuilder label(String label) {
            Preconditions.checkNotNull(label, "Button label cannot be null");
            button.label = label;
            logger.atFine().log("label() called, set label: %s", label);
            return this;
        }

        /**
         * Builds the button.
         * @return built button
         */
        @Override
        public Button build() {
            logger.atFine().log("build() called, returning button: %s", button);
            return button;
        }
    }
    //endregion
}
//endregion
