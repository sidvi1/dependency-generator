package ru.sidvi.depextractor.formatters;


import ru.sidvi.depextractor.model.Info;

/**
 * Форматирует Info при приобразовании в строку.
 */
public interface Formatter {
    String format(Info info);
}
