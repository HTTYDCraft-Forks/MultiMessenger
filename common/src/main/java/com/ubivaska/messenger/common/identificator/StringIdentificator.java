package com.ubivaska.messenger.common.identificator;

public class StringIdentificator implements Identificator {
	private final String id;

	public StringIdentificator(String id) {
		this.id = id;
	}

	@Override
	public String asString() {
		return id;
	}

	@Override
	public boolean isString() {
		return true;
	}
}
