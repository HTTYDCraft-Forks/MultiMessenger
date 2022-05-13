package com.ubivaska.messenger.common.identificator;

import com.ubivashka.functions.Castable;

public interface Identificator extends Castable<Identificator> {
	default String asString() {
		throw new UnsupportedOperationException();
	}

	default long asNumber() {
		throw new UnsupportedOperationException();
	}

	default boolean isString() {
		return false;
	}

	default boolean isNumber() {
		return false;
	}

	default Object asObject() {
		if (isNumber())
			return asNumber();
		if (isString())
			return asString();
		return null;
	}
}
