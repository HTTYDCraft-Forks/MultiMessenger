package com.ubivashka.messenger.common.file;

import java.io.File;
import java.util.Optional;

public class DefaultMessengerFile implements MessengerFile {
	private final File file;
	private FileType fileType;

	public DefaultMessengerFile(File file) {
		this.file = file;
		String fileExtension = Optional.ofNullable(file.getName()).filter(name -> name.contains("."))
				.map(name -> name.substring(name.lastIndexOf('.') + 1)).orElse("");
		switch (fileExtension) {
		case "png":
		case "jpeg":
			fileType = FileType.PHOTO;
			break;
		case "mp3":
			fileType = FileType.AUDIO;
			break;
		case "mp4":
			fileType = FileType.VIDEO;
			break;
		default:
			fileType = FileType.DOCUMENT;
		}
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public FileType getFileType() {
		return fileType;
	}
}
