package com.httydcraft.multimessenger.core.identificator;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.multimessenger.core.function.Castable;

/**
 * Interface representing an identifier abstraction that can be a string or a number.
 */
//region Identificator Interface
public interface Identificator extends Castable<Identificator> {
    /**
     * Gets the identifier as a string.
     * @return string value
     * @throws UnsupportedOperationException if not supported
     */
    default String asString() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the identifier as a number.
     * @return numeric value
     * @throws UnsupportedOperationException if not supported
     */
    default long asNumber() {
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if this identifier is a string.
     * @return true if string
     */
    default boolean isString() {
        return false;
    }

    /**
     * Checks if this identifier is a number.
     * @return true if number
     */
    default boolean isNumber() {
        return false;
    }

    /**
     * Gets the identifier as an object (string or number).
     * @return object value
     */
    default Object asObject() {
        if (isNumber())
            return asNumber();
        if (isString())
            return asString();
        return null;
    }

    /**
     * Creates an Identificator from an Object.
     * @param object object to convert
     * @return Identificator instance or null
     */
    static Identificator fromObject(Object object) {
        GoogleLogger logger = GoogleLogger.forEnclosingClass();
        logger.atFine().log("Identificator.fromObject() called with: %s", object);
        if (object instanceof Long)
            return new NumberIdentificator((Long) object);
        if (object instanceof Integer)
            return new NumberIdentificator((Integer) object);
        if (object instanceof String)
            return new StringIdentificator((String) object);
        return null;
    }

    /**
     * Creates a numeric Identificator.
     * @param id numeric id
     * @return Identificator instance
     */
    static Identificator of(long id) {
        GoogleLogger logger = GoogleLogger.forEnclosingClass();
        logger.atFine().log("Identificator.of(long) called with: %d", id);
        return new NumberIdentificator(id);
    }

    /**
     * Creates a string Identificator.
     * @param id string id
     * @return Identificator instance
     * @throws NullPointerException if id is null
     */
    static Identificator of(String id) {
        GoogleLogger logger = GoogleLogger.forEnclosingClass();
        Preconditions.checkNotNull(id, "id cannot be null");
        logger.atFine().log("Identificator.of(String) called with: %s", id);
        return new StringIdentificator(id);
    }
}
//endregion
