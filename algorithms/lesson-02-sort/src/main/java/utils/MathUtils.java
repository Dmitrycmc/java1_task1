package utils;

public class MathUtils {
    public static int randomInt(int max) {
        return (int) (Math.random() * (max + 1));
    }

    public static int randomInt(int min, int max) {
        return randomInt(max - min) + min;
    }

    public static int randomInt(int min, int max, int step) {
        return randomInt((max - min) / step) * step + min;
    }
}
