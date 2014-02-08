package ru.sidvi.depextractor;

import static ru.sidvi.depextractor.ComparatorUtils.isMetainfDir;
import static ru.sidvi.depextractor.ComparatorUtils.isPomXml;

/**
 * Created by sidvi on 08.02.14.
 */
public class PomPathComparator implements PathComparator {
    @Override
    public boolean isValid(String name) {
        return isMetainfDir(name) && isPomXml(name);
    }
}
