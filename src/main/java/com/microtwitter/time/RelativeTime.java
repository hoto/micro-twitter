package com.microtwitter.time;

import java.util.concurrent.TimeUnit;

public class RelativeTime {

    /**
     * Converts milliseconds from a number to a human readable 'time ago' format.
     */
    public String toElapsedDuration(long durationInMs) {
        if (durationInMs < 0) return "";
        long days = TimeUnit.MILLISECONDS.toDays(durationInMs);
        long hours = TimeUnit.MILLISECONDS.toHours(durationInMs);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMs);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(durationInMs);
        if (days > 0)
            return ago(days, "day");
        if (hours > 0)
            return ago(hours, "hour");
        if (minutes > 0)
            return ago(minutes, "minute");
        if (seconds > 0)
            return ago(seconds, "second");
        return "now";
    }

    private String ago(long amount, String unit) {
        return amount == 1 ? String.format("%d %s ago", amount, unit) : String.format("%d %ss ago", amount, unit);
    }
}
