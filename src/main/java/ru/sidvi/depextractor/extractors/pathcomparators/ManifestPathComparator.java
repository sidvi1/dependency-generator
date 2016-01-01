package ru.sidvi.depextractor.extractors.pathcomparators;


/**
 * Проверяет на соответствие MANIFEST.MF.
 */
public class ManifestPathComparator implements PathComparator {
    public boolean isValid(String name) {
        return ComparatorUtils.isMetainfDir(name) && ComparatorUtils.isManifestMf(name);
    }
}