package ru.sidvi.depextractor.formatters;


import ru.sidvi.depextractor.model.JarInfo;

/**
 * Осущствляет простое столбчатое форматирование.
 */
public class CsvFormatter implements Formatter {

    private String separator;

    public CsvFormatter(String separator) {
        this.separator = separator;
    }

    @Override
    public String format(JarInfo info) {
        return info.getFormattedFileName("%s") + separator
                + info.getFormattedSource("%s") + separator
                + info.getFormattedResult("%s" + separator + "%s" + separator + "%s")
                + "\r\n";
    }
}
