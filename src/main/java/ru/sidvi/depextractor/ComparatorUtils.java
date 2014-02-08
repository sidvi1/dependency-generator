package ru.sidvi.depextractor;

/**
 * Created by sidvi on 08.02.14.
 */
public class ComparatorUtils {
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
