package ru.sidvi.depextractor.pathcomparators;

/**
 * Created by Vitaly A. Sidorov on 08.02.14.
 */
public class PomPathComparator implements PathComparator {
    public boolean isValid(String name) {
        return ComparatorUtils.isMetainfDir(name) && ComparatorUtils.isPomXml(name);
    }
}
