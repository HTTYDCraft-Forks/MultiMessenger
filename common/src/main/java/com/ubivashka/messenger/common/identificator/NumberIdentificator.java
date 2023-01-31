package com.ubivashka.messenger.common.identificator;

public class NumberIdentificator implements Identificator {
	private final long id;

	public NumberIdentificator(long id) {
		this.id = id;
	}

	@Override
	public long asNumber() {
		return id;
	}

	@Override
	public boolean isNumber() {
		return true;
	}
}
