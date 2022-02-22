package ru.geekbrains.utils;

public class TimeUtils {
    public static String format(long time) {
        long ms = time % 1000;
        long s = time / 1000;

        long m = s / 60;
        s %= 60;

        long h = m / 60;
        m %= 60;

        StringBuilder sb = new StringBuilder();
        if (h > 0) {
            if (sb.toString().length() > 0) {
                sb.append(" ");
            }
            sb.append(h + " h");
        }
        if (m > 0) {
            if (sb.toString().length() > 0) {
                sb.append(" ");
            }
            sb.append(m + " min");
        }
        if (s > 0) {
            if (sb.toString().length() > 0) {
                sb.append(" ");
            }
            sb.append(s + " sec");
        }
        if (ms > 0) {
            if (sb.toString().length() > 0) {
                sb.append(" ");
            }
            sb.append(ms + " ms");
        }

        return sb.toString().length() > 0 ? sb.toString() : "0 ms";
    }
}
