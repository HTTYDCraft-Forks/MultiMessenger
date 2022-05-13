package com.ubivaska.messenger.common.button;

import com.ubivashka.functions.Castable;

public interface Button extends Castable<Button> {
	String getLabel();

	String getActionData();
	
	ButtonColor getColor();

	ButtonAction getAction();

	public interface ButtonBuilder {
		ButtonBuilder action(ButtonAction action);
		
		ButtonBuilder actionData(String actionData);
		
		ButtonBuilder color(ButtonColor color);
		
		ButtonBuilder label(String label);
		
		Button build();
	}
}
