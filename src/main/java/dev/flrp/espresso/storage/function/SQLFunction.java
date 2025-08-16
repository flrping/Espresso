package dev.flrp.espresso.storage.function;

@FunctionalInterface
public interface SQLFunction<T, R> {

    R apply(T t) throws Exception;

}
