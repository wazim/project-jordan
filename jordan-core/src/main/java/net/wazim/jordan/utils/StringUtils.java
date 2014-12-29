package net.wazim.jordan.utils;

public class StringUtils {

    public static String stripPunctuation(String title) {
        return title.replace(":", "")
                .replace("-", "")
                .replace("_", "")
                .replace("'", "")
                .replace("\"", "")
                .replace(".", "");
    }

}
