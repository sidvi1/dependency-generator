package ru.sidvi.depextractor.formatters;

import ru.sidvi.depextractor.JarInfo;

/**
 * Created by sidvi on 07.02.14.
 */
public class InlineFormatter implements Formatter {

    private static final String format = "%-30s %-50s %-10s%n";

    @Override
    public String format(JarInfo info) {

        return String.format(format, info.getFormatedName("%s"), info.getFormatedSource("%s"), info.getFormattedResult("%s:%s:%s"));
    }

    public String getFormat() {
        return format;
    }
}
