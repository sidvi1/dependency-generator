package ru.sidvi.depextractor.pathcomparators;

/**
 * Типовые методы использующиеся в Comparators.
 */
class ComparatorUtils {
    private ComparatorUtils() {
    }

    public static boolean isPomXml(String file) {
        return file.endsWith("pom.xml");
    }

    static boolean isMetainfDir(String file) {
        return file.startsWith("META-INF");
    }

    static boolean isManifestMf(String file) {
        return file.toLowerCase().endsWith("manifest.mf".toLowerCase());
    }
}
