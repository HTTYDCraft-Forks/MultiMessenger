package com.ubivaska.messenger.common.button;

import com.ubivashka.functions.Castable;

public interface ButtonAction extends Castable<ButtonAction>{
	public static interface ButtonActionBuilder {
		ButtonAction callback();
		
		ButtonAction link();
		
		ButtonAction reply();
	}
}
