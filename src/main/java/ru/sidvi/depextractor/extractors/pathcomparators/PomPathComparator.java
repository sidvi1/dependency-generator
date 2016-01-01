package ru.sidvi.depextractor.extractors.pathcomparators;

/**
 * Проверяет на соотвтествие pom.xml.
 */
public class PomPathComparator implements PathComparator {
    public boolean isValid(String name) {
        return ComparatorUtils.isMetainfDir(name) && ComparatorUtils.isPomXml(name);
    }
}
