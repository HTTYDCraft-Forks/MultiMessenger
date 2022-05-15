package com.ubivashka.messenger.telegram.message;

import java.util.ArrayList;
import java.util.List;

import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendAudio;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SendVideo;
import com.ubivashka.messenger.telegram.message.keyboard.TelegramKeyboard;
import com.ubivashka.messenger.telegram.providers.TelegramApiProvider;
import com.ubivaska.messenger.common.ApiProvider;
import com.ubivaska.messenger.common.file.MessengerFile;
import com.ubivaska.messenger.common.identificator.Identificator;
import com.ubivaska.messenger.common.message.DefaultMessage;

public class TelegramMessage extends DefaultMessage {
	private static TelegramApiProvider defaultApiProvider;

	public TelegramMessage(String text) {
		super(text);
	}

	@Override
	public void send(Identificator identificator) {
		if (defaultApiProvider == null)
			throw new NullPointerException(
					"Default vk api provider was not defined. Define with static TelegramMessage#setDefaultApiProvider method!");
		send(identificator, defaultApiProvider);
	}

	@Override
	public void send(Identificator identificator, ApiProvider apiProvider) {
		SendMessage sendMessage = new SendMessage(identificator.asObject(), text);
		if (keyboard != null && keyboard.safeAs(TelegramKeyboard.class).isPresent())
			sendMessage.replyMarkup(keyboard.as(TelegramKeyboard.class).create());
		if (replyIdentificator != null && replyIdentificator.isNumber())
			sendMessage.replyToMessageId((int) replyIdentificator.asNumber());
		apiProvider.as(TelegramApiProvider.class).getBot().execute(sendMessage);
		if (files != null && files.length != 0)
			toEntities(identificator, files)
					.forEach(request -> apiProvider.as(TelegramApiProvider.class).getBot().execute(request));
	}

	private List<BaseRequest<?, ?>> toEntities(Identificator identificator, MessengerFile... files) {
		List<BaseRequest<?, ?>> requests = new ArrayList<>();
		for (MessengerFile file : files) {
			BaseRequest<?, ?> request = toRequest(identificator, file);
			if (request != null)
				requests.add(request);
		}
		return requests;
	}

	public static TelegramApiProvider getDefaultApiProvider() {
		return defaultApiProvider;
	}

	public static void setDefaultApiProvider(TelegramApiProvider defaultApiProvider) {
		TelegramMessage.defaultApiProvider = defaultApiProvider;
	}

	private BaseRequest<?, ?> toRequest(Identificator identificator, MessengerFile file) {
		switch (file.getFileType()) {
		case AUDIO:
			return new SendAudio(identificator.asObject(), file.getFile());
		case DOCUMENT:
			return new SendDocument(identificator.asObject(), file.getFile());
		case PHOTO:
			return new SendPhoto(identificator.asObject(), file.getFile());
		case VIDEO:
			return new SendVideo(identificator.asObject(), file.getFile());
		case OTHER:
		default:
			break;

		}
		return null;
	}

	public class TelegramMessageBuilder extends DefaultMessageBuilder {
		public TelegramMessageBuilder() {
			super(TelegramMessage.this);
		}
	}
}
