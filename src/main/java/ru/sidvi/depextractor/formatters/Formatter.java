package ru.sidvi.depextractor.formatters;

import ru.sidvi.depextractor.JarInfo;

/**
 * Created by Vitaly A. Sidorov on 05.02.14.
 */
public interface Formatter {
    String format(JarInfo info);

    String getFormat();
}
