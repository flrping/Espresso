package dev.flrp.espresso.storage.exception;

@FunctionalInterface
public interface SQLFunction<T, R> {
    R apply(T t) throws Exception;
}
