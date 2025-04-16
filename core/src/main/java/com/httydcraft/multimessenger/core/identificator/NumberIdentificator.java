package com.httydcraft.multimessenger.core.identificator;

import com.google.common.flogger.GoogleLogger;

/**
 * Implementation of Identificator for numeric (long) identifiers.
 */
//region NumberIdentificator Class
public class NumberIdentificator implements Identificator {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private final long id;

    /**
     * Constructs a NumberIdentificator with the given id.
     * @param id numeric identifier
     */
    public NumberIdentificator(long id) {
        this.id = id;
        logger.atFine().log("NumberIdentificator created with id: %d", id);
    }

    /**
     * Gets the identifier as a number.
     * @return numeric id
     */
    @Override
    public long asNumber() {
        logger.atFine().log("asNumber() called, returning: %d", id);
        return id;
    }

    /**
     * Checks if this identifier is a number.
     * @return true
     */
    @Override
    public boolean isNumber() {
        logger.atFine().log("isNumber() called, returning: true");
        return true;
    }
}
//endregion
