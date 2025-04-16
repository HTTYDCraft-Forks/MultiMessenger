package com.httydcraft.multimessenger.core.function;

import com.google.common.flogger.GoogleLogger;
import java.util.Optional;

/**
 * Interface for safe and unsafe casting of objects, useful for interfaces and abstract classes.
 * @param <T> the type of the castable object
 */
//region Castable Interface
public interface Castable<T> {
    /**
     * Casts this object to the provided one. Useful for interfaces or abstract classes. Method may throw {@link ClassCastException}
     * @param <R> the type to cast to
     * @param clazz R class that will cast this object
     * @return casted object
     * @throws ClassCastException if the cast is not possible
     */
    default <R extends T> R as(Class<R> clazz) {
        GoogleLogger logger = GoogleLogger.forEnclosingClass();
        logger.atFine().log("as() called with class: %s", clazz);
        return clazz.cast(this);
    }

    /**
     * Casts this to the provided one. Returns {@linkplain Optional#empty()} if cannot cast this object
     * @param <R> the type to cast to
     * @param clazz R class that will cast this object
     * @return Optional value of casted object
     */
    default <R extends T> Optional<R> safeAs(Class<R> clazz) {
        GoogleLogger logger = GoogleLogger.forEnclosingClass();
        logger.atFine().log("safeAs(Class) called with class: %s", clazz);
        return Optional.ofNullable(safeAs(clazz, null));
    }

    /**
     * Casts this to the provided one. Returns default if cannot cast this object
     * @param <R> the type to cast to
     * @param clazz R class that will cast this object
     * @param defaultValue Default value if cannot cast object.
     * @return casted object or default
     */
    default <R extends T> R safeAs(Class<R> clazz, R defaultValue) {
        GoogleLogger logger = GoogleLogger.forEnclosingClass();
        logger.atFine().log("safeAs(Class, default) called with class: %s, default: %s", clazz, defaultValue);
        try {
            return clazz.cast(this);
        } catch(ClassCastException ignored) {
            logger.atWarning().log("ClassCastException in safeAs, returning default value");
            return defaultValue;
        }
    }
}
//endregion
