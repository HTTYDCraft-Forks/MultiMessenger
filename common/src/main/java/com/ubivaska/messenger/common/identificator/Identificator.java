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

	static Identificator fromObject(Object object) {
		if (object instanceof Long)
			return new NumberIdentificator((Long) object);
		if (object instanceof Integer)
			return new NumberIdentificator((Integer) object);
		if (object instanceof String)
			return new StringIdentificator((String) object);
		return null;
	}
	
	static Identificator of(long id) {
		return new NumberIdentificator(id);
	}
	
	static Identificator of(String id) {
		return new StringIdentificator(id);
	}
}
