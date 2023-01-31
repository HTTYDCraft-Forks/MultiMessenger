package com.ubivashka.messenger.common.file;

import java.io.File;

public interface MessengerFile {
    File getFile();

    FileType getFileType();

    static MessengerFile of(File file) {
        return new DefaultMessengerFile(file);
    }
}
