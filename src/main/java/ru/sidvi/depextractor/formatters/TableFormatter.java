package ru.sidvi.depextractor.formatters;


import ru.sidvi.depextractor.model.JarInfo;

/**
 * Осущствляет простое столбчатое форматирование.
 */
public class TableFormatter implements Formatter {

    private static final String format = "%-30s %-50s %-3s%n";

    @Override
    public String format(JarInfo info) {
        return String.format(format, info.getFormattedFileName("%s"), info.getFormattedSource("%s"), info.getFormattedResult("%s:%s:%s"));
    }

}
