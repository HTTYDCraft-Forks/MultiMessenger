package com.httydcraft.multimessenger.telegram.message.keyboard.button;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.button.ButtonAction;

/**
 * Telegram implementation of the ButtonAction interface.
 */
//region TelegramButtonAction Class
public class TelegramButtonAction implements ButtonAction {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private final TelegramButtonType buttonType;

    /**
     * Constructs a TelegramButtonAction with the given type.
     * @param buttonAction TelegramButtonType
     */
    public TelegramButtonAction(TelegramButtonType buttonAction) {
        this.buttonType = buttonAction;
        logger.atFine().log("TelegramButtonAction created with type: %s", buttonAction);
    }

    /**
     * Gets the TelegramButtonType.
     * @return button type
     */
    public TelegramButtonType getButtonType() {
        logger.atFine().log("getButtonType() called, returning: %s", buttonType);
        return buttonType;
    }

    /**
     * Telegram button types.
     */
    public enum TelegramButtonType {
        REPLY, CALLBACK, OPEN_LINK
    }

    /**
     * Builder for TelegramButtonAction.
     */
    //region Builder Class
    public static class Builder implements ButtonActionBuilder {
        @Override
        public ButtonAction callback() {
            logger.atFine().log("Builder.callback() called");
            return new TelegramButtonAction(TelegramButtonType.CALLBACK);
        }

        @Override
        public ButtonAction link() {
            logger.atFine().log("Builder.link() called");
            return new TelegramButtonAction(TelegramButtonType.OPEN_LINK);
        }

        @Override
        public ButtonAction reply() {
            logger.atFine().log("Builder.reply() called");
            return new TelegramButtonAction(TelegramButtonType.REPLY);
        }
    }
    //endregion
}
//endregion
