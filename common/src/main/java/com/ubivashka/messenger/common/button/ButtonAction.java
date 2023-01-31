package com.ubivashka.messenger.common.button;

import com.ubivashka.functions.Castable;

public interface ButtonAction extends Castable<ButtonAction> {
    interface ButtonActionBuilder {
        ButtonAction callback();

        ButtonAction link();

        ButtonAction reply();
    }
}
