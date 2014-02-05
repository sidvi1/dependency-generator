package ru.sidvi.depextractor;

/**
 * Created by sidvi on 05.02.14.
 */
public class FormatterUtils {
    private static int STEP_SIZE = 4;

    static String pad(int step) {
        String result = "";
        for (int i = 0; i < step*STEP_SIZE; i++) {
            result += " ";
        }
        return result;
    }

    static String tag(String name, String value) {
        return "<" + name + ">" + value + "</" + name + ">";
    }
}
