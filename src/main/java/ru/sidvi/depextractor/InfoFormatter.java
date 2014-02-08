package ru.sidvi.depextractor;

/**
 * Created by sidvi on 05.02.14.
 */
public interface InfoFormatter {
    String format(JarInfo info);

    String getFormat();
}
