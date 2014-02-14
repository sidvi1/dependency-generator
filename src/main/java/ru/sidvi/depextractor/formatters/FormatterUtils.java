package ru.sidvi.depextractor.formatters;

/**
 * Created by sidvi on 05.02.14.
 */
public class FormatterUtils {
    private final static int STEP_SIZE = 4;

    static String pad(int step) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < step * STEP_SIZE; i++) {
            result.append(" ");
        }
        return result.toString();
    }

    static String tag(String name, String value) {
        return "<" + name + ">" + value + "</" + name + ">";
    }
}
