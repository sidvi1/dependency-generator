package ru.sidvi.depextractor;

/**
 * Created by sidvi on 07.02.14.
 */
public class InlineFormatter implements InfoFormatter {

    private static final String format = "%-30s %-50s %-10s%n";

    @Override
    public String format(BaseInfo info) {

        return String.format(format, info.getFormatedName("%s"), info.getFormatedSource("%s"), info.getFormattedResult("%s:%s:%s"));
    }

    public String getFormat(){
        return format;
    }
}
