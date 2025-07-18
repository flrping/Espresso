package dev.flrp.espresso.storage.exception;

@FunctionalInterface
public interface SQLConsumer<T> {
    void accept(T t) throws Exception;
}
