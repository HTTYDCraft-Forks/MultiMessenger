package com.httydcraft.multimessenger.core.identificator;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;

/**
 * Implementation of Identificator for string identifiers.
 */
//region StringIdentificator Class
public class StringIdentificator implements Identificator {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private final String id;

    /**
     * Constructs a StringIdentificator with the given id.
     * @param id string identifier
     * @throws NullPointerException if id is null
     */
    public StringIdentificator(String id) {
        Preconditions.checkNotNull(id, "id cannot be null");
        this.id = id;
        logger.atFine().log("StringIdentificator created with id: %s", id);
    }

    /**
     * Gets the identifier as a string.
     * @return string id
     */
    @Override
    public String asString() {
        logger.atFine().log("asString() called, returning: %s", id);
        return id;
    }

    /**
     * Checks if this identifier is a string.
     * @return true
     */
    @Override
    public boolean isString() {
        logger.atFine().log("isString() called, returning: true");
        return true;
    }
}
//endregion
