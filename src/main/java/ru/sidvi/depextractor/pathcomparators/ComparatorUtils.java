package ru.sidvi.depextractor.pathcomparators;

/**
 * Created by Vitaly A. Sidorov on 08.02.14.
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
