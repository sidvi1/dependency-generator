package ru.sidvi.depextractor.formatters;

import ru.sidvi.depextractor.extractors.JarInfo;

/**
 * ����������� JarInfo ��� �������������� � ������.
 */
public interface Formatter {
    String format(JarInfo info);
}
