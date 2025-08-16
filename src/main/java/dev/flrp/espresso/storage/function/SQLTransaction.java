package dev.flrp.espresso.storage.function;

@FunctionalInterface
public interface SQLTransaction {

    void run() throws Exception;

}

