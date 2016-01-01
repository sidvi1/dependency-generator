package ru.sidvi.depextractor.formatters;


import ru.sidvi.depextractor.model.Info;

/**
 * Осущствляет простое столбчатое форматирование.
 */
public class InlineFormatter implements Formatter {

    private static final String format = "%-30s %-50s %-10s%n";

    public String format(Info info) {
        return String.format(format, info.getFormattedName("%s"), info.getFormattedSource("%s"), info.getFormattedResult("%s:%s:%s"));
    }

}
