package com.ubivashka.messenger.telegram.providers;

import com.pengrad.telegrambot.TelegramBot;

public class DefaultTelegramApiProvider implements TelegramApiProvider {
	private final TelegramBot bot;

	public DefaultTelegramApiProvider(TelegramBot bot) {
		this.bot = bot;
	}

	@Override
	public TelegramBot getBot() {
		return bot;
	}
}
