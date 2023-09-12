package dev.flrp.espresso.utils;

public class StringUtils {

    /**
     * Some plugins support block directions, so they will have "_z", "_y", or "_x" at the end of their ID.
     * @param input The outcome of getId().
     * @return The appropriate ItemsAdder name.
     */
    public static String getItemsAdderName(String input) {
        if (input.endsWith("_z") || input.endsWith("_y") || input.endsWith("_x")) {
            input = input.substring(0, input.length() - 2); // Remove the ending if it has "_z", "_y", or "_x"
        }

        return input;
    }

}
