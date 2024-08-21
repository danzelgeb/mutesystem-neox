package de.danzel34.mutesystem.utils;

public class TimeUtils {

    public static Long getMillis(String time) {
        if (time.endsWith("s")) {
            return Long.parseLong(time.replace("s", "")) * 1000;
        } else if (time.endsWith("m")) {
            return Long.parseLong(time.replace("m", "")) * 1000 * 60;
        } else if (time.endsWith("h")) {
            return Long.parseLong(time.replace("h", "")) * 1000 * 60 * 60;
        } else if (time.endsWith("d")) {
            return Long.parseLong(time.replace("d", "")) * 1000 * 60 * 60 * 24;
        } else {
            return null;
        }
    }
}
