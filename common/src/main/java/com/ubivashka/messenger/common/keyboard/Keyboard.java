package com.ubivashka.messenger.common.keyboard;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.ubivashka.functions.Castable;
import com.ubivashka.messenger.common.button.Button;

public interface Keyboard extends Castable<Keyboard> {
	/**
	 * Returns copied 2d list of buttons
	 * 
	 * @return 2d list of buttons.
	 */
	List<List<Button>> getButtons();

	Keyboard addRow(Button... buttons);

	Keyboard removeIf(Predicate<Button> buttonFilter);

	Keyboard ifThen(Predicate<Button> filter, Function<Button, Button> function);

	KeyboardType getType();

	interface KeyboardType extends Castable<KeyboardType> {
		default boolean isInline() {
			return false;
		}

		default boolean isReply() {
			return false;
		}

		static KeyboardType inline() {
			return new KeyboardType() {
				@Override
				public boolean isInline() {
					return true;
				}
			};
		}

		static KeyboardType reply() {
			return new KeyboardType() {
				@Override
				public boolean isReply() {
					return true;
				}
			};
		}
	}

	public interface KeyboardBuilder {
		KeyboardBuilder button(int row, Button button);

		KeyboardBuilder buttons(List<List<Button>> buttons);

		KeyboardBuilder row(Button... buttons);

		KeyboardBuilder type(KeyboardType type);

		Keyboard build();
	}
}
