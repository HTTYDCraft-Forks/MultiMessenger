package com.ubivaska.messenger.common.button;

import com.ubivashka.functions.Castable;

public interface ButtonColor extends Castable<ButtonColor> {
	public interface ButtonColorBuilder {
		ButtonColor red();

		ButtonColor blue();

		ButtonColor green();

		ButtonColor white();

		ButtonColor grey();

		static ButtonColorBuilder unsupportedBuilder() {
			return new ButtonColorBuilder() {
				@Override
				public ButtonColor white() {
					return empty();
				}

				@Override
				public ButtonColor red() {
					return empty();
				}

				@Override
				public ButtonColor grey() {
					return empty();
				}

				@Override
				public ButtonColor green() {
					return empty();
				}

				@Override
				public ButtonColor blue() {
					return empty();
				}

				private ButtonColor empty() {
					return new ButtonColor() {
					};
				}
			};
		}
	}
}
