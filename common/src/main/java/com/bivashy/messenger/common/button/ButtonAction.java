package com.bivashy.messenger.common.button;

import com.bivashy.messenger.common.function.Castable;

public interface ButtonAction extends Castable<ButtonAction> {
    interface ButtonActionBuilder {
        static ButtonActionBuilder unsupportedBuilder() {
            return new ButtonActionBuilder() {
                @Override
                public ButtonAction callback() {
                    return empty();
                }

                @Override
                public ButtonAction link() {
                    return empty();
                }

                @Override
                public ButtonAction reply() {
                    return empty();
                }

                private ButtonAction empty() {
                    return new ButtonAction() {
                    };
                }
            };
        }

        ButtonAction callback();

        ButtonAction link();

        ButtonAction reply();
    }
}
