package com.httydcraft.multimessenger.core.file;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import java.io.File;

/**
 * Interface representing a messenger file abstraction with type information.
 */
//region MessengerFile Interface
public interface MessengerFile {
    /**
     * Gets the file object.
     * @return the file
     */
    File getFile();

    /**
     * Gets the file type.
     * @return file type
     */
    FileType getFileType();

    /**
     * Factory method to create a MessengerFile from a File.
     * @param file file to wrap
     * @return MessengerFile instance
     * @throws NullPointerException if file is null
     */
    static MessengerFile of(File file) {
        GoogleLogger logger = GoogleLogger.forEnclosingClass();
        Preconditions.checkNotNull(file, "File cannot be null");
        logger.atFine().log("MessengerFile.of() called for file: %s", file.getName());
        return new DefaultMessengerFile(file);
    }
}
//endregion
