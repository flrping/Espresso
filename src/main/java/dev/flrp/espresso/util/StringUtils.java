package dev.flrp.espresso.util;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern hexPattern = Pattern.compile("<#([A-Fa-f0-9]){6}>");

    /**
     * Some plugins support block directions, so they will have "_z", "_y", or "_x" at the end of their ID.
     * This will also remove the namespaceID "example:" if present.
     * @param input The outcome of getId().
     * @return The appropriate ItemsAdder name.
     */
    public static String getItemsAdderName(String input) {
        if (input.endsWith("_z") || input.endsWith("_y") || input.endsWith("_x")) {
            input = input.substring(0, input.length() - 2); // Remove the ending if it has "_z", "_y", or "_x"
        }
        if (input.contains(":")) {
            input = input.split(":")[1]; // Remove the namespaceID if present
        }

        return input;
    }

    /**
     * This will be used to parse color codes and hex if present.
     * @param input The string to parse.
     * @return The parsed string.
     */
    public static String parseColor(String input) {
        Matcher matcher = hexPattern.matcher(input);
        while (matcher.find()) {
            String hex = input.substring(matcher.start(), matcher.end());
            input = input.replace(hex, ChatColor.of(hex.substring(1, hex.length() - 1)).toString());
            matcher = hexPattern.matcher(input);
        }
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> parseColor(List<String> input) {
        List<String> output = new ArrayList<>();
        for (String string : input) {
            output.add(parseColor(string));
        }
        return output;
    }

}
