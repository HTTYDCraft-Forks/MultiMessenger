package com.bivashy.messenger.common.button;

import com.bivashy.messenger.common.function.Castable;

public interface ButtonAction extends Castable<ButtonAction> {
    interface ButtonActionBuilder {
        ButtonAction callback();

        ButtonAction link();

        ButtonAction reply();
    }
}
