package com.httydcraft.multimessenger.core.file;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import java.io.File;
import java.util.Optional;

/**
 * Default implementation of MessengerFile for handling files and their types.
 */
//region DefaultMessengerFile Class
public class DefaultMessengerFile implements MessengerFile {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();

    private final File file;
    private FileType fileType;

    /**
     * Constructs a DefaultMessengerFile and determines its FileType based on the file extension.
     * @param file the file to wrap
     * @throws NullPointerException if file is null
     */
    public DefaultMessengerFile(File file) {
        Preconditions.checkNotNull(file, "File cannot be null");
        this.file = file;
        logger.atFine().log("DefaultMessengerFile constructor called for file: %s", file.getName());
        String fileExtension = Optional.of(file.getName()).filter(name -> name.contains("."))
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
        logger.atFine().log("FileType determined: %s", fileType);
    }

    /**
     * Gets the wrapped file.
     * @return the file
     */
    @Override
    public File getFile() {
        logger.atFine().log("getFile() called");
        return file;
    }

    /**
     * Gets the file type.
     * @return the file type
     */
    @Override
    public FileType getFileType() {
        logger.atFine().log("getFileType() called");
        return fileType;
    }
}
//endregion
