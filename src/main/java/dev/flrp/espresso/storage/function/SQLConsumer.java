package dev.flrp.espresso.storage.function;

@FunctionalInterface
public interface SQLConsumer<T> {

    void accept(T t) throws Exception;

}
