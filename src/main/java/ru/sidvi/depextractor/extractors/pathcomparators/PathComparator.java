package ru.sidvi.depextractor.extractors.pathcomparators;

/**
 * Определяет является ли path, искомым именем пути.
 */
public interface PathComparator {
    boolean isValid(String path);
}
