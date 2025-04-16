package com.httydcraft.multimessenger.core.button;

import com.httydcraft.multimessenger.core.function.Castable;

/**
 * Interface for buttons in the messenger UI.
 */
//region Button Interface
public interface Button extends Castable<Button> {
    /**
     * Gets the button label.
     * @return label
     */
    String getLabel();

    /**
     * Gets the button action data.
     * @return action data
     */
    String getActionData();

    /**
     * Gets the button color.
     * @return color
     */
    ButtonColor getColor();

    /**
     * Gets the button action.
     * @return action
     */
    ButtonAction getAction();

    /**
     * Builder for Button implementations.
     */
    //region ButtonBuilder Interface
    interface ButtonBuilder {
        /**
         * Sets the button action.
         * @param action button action
         * @return this builder
         */
        ButtonBuilder action(ButtonAction action);

        /**
         * Sets the button action data.
         * @param actionData action data
         * @return this builder
         */
        ButtonBuilder actionData(String actionData);

        /**
         * Sets the button color.
         * @param color button color
         * @return this builder
         */
        ButtonBuilder color(ButtonColor color);

        /**
         * Sets the button label.
         * @param label button label
         * @return this builder
         */
        ButtonBuilder label(String label);

        /**
         * Builds the button.
         * @return built button
         */
        Button build();
    }
    //endregion
}
//endregion
