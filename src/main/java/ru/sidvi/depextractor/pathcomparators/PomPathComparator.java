package ru.sidvi.depextractor.pathcomparators;

/**
 * Created by sidvi on 08.02.14.
 */
public class PomPathComparator implements PathComparator {
    @Override
    public boolean isValid(String name) {
        return ComparatorUtils.isMetainfDir(name) && ComparatorUtils.isPomXml(name);
    }
}
