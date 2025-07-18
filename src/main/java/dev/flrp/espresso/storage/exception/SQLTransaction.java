package dev.flrp.espresso.storage.exception;

@FunctionalInterface
public interface SQLTransaction {
    void run() throws Exception;
}

