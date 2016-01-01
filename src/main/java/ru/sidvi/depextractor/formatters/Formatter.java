package ru.sidvi.depextractor.formatters;

import ru.sidvi.depextractor.extractors.JarInfo;

/**
 * Форматирует JarInfo при приобразовании в строку.
 */
public interface Formatter {
    String format(JarInfo info);
}
