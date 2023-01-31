package com.ubivashka.messenger.telegram.message.keyboard.button;

import com.ubivashka.messenger.common.button.ButtonAction;

public class TelegramButtonAction implements ButtonAction {
    private final TelegramButtonType buttonType;

    public TelegramButtonAction(TelegramButtonType buttonAction) {
        this.buttonType = buttonAction;
    }

    public TelegramButtonType getButtonType() {
        return buttonType;
    }

    public static class TelegramButtonActionBuilder implements ButtonActionBuilder {
        @Override
        public ButtonAction callback() {
            return new TelegramButtonAction(TelegramButtonType.CALLBACK);
        }

        @Override
        public ButtonAction link() {
            return new TelegramButtonAction(TelegramButtonType.OPEN_LINK);
        }

        @Override
        public ButtonAction reply() {
            return new TelegramButtonAction(TelegramButtonType.REPLY);
        }
    }
    public enum TelegramButtonType {
        REPLY, CALLBACK, OPEN_LINK
    }
}
