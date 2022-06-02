package com.ubivaska.messenger.common.keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.ubivaska.messenger.common.button.Button;

public class DefaultKeyboard implements Keyboard {
	protected List<List<Button>> buttons = new ArrayList<>();
	protected KeyboardType keyboardType;

	@Override
	public List<List<Button>> getButtons() {
		return new ArrayList<>(buttons);
	}

	@Override
	public Keyboard addRow(Button... buttons) {
		this.buttons.add(Arrays.asList(buttons));
		return this;
	}

	@Override
	public Keyboard removeIf(Predicate<Button> buttonFilter) {
		buttons.forEach(buttonsList -> buttonsList.removeIf(buttonFilter));
		buttons.removeIf(List::isEmpty);
		return this;
	}

	@Override
	public Keyboard ifThen(Predicate<Button> filter, Function<Button, Button> function) {
		buttons.stream().map(buttonsList -> buttonsList.stream().map(button -> {
			if (filter.test(button))
				return function.apply(button);
			return button;
		}).collect(Collectors.toList()));
		return this;
	}

	@Override
	public KeyboardType getType() {
		return keyboardType;
	}

	public static abstract class DefaultKeyboardBuilder implements KeyboardBuilder {
		private final DefaultKeyboard keyboard;

		public DefaultKeyboardBuilder(DefaultKeyboard keyboard) {
			this.keyboard = keyboard;
		}

		@Override
		public KeyboardBuilder button(int row, Button button) {
			while (keyboard.buttons.size() <= row)
				keyboard.buttons.add(new ArrayList<>());
			keyboard.buttons.get(row).add(button);
			return this;
		}

		@Override
		public KeyboardBuilder buttons(List<List<Button>> buttons) {
			keyboard.buttons = buttons;
			return this;
		}

		@Override
		public KeyboardBuilder row(Button... buttons) {
			keyboard.addRow(buttons);
			return this;
		}

		@Override
		public KeyboardBuilder type(KeyboardType type) {
			keyboard.keyboardType = type;
			return this;
		}

		@Override
		public Keyboard build() {
			return keyboard;
		}
	}
}
