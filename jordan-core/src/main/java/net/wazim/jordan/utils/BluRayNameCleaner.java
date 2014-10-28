package net.wazim.jordan.utils;

public class BluRayNameCleaner {

    public static String cleanName(String bluRayName) {
        return bluRayName
                .replace("[", "(")
                .replace("]", ")")
                .replace("+", "-")
                .replace("&", "and")
                ;
    }

}
