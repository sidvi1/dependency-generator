package ru.sidvi.depextractor;


import static ru.sidvi.depextractor.ComparatorUtils.isManifestMf;
import static ru.sidvi.depextractor.ComparatorUtils.isMetainfDir;

/**
 * Created by sidvi on 08.02.14.
 */
public class ManifestPathComparator implements PathComparator {
    @Override
    public boolean isValid(String name) {
        return isMetainfDir(name) && isManifestMf(name);
    }
}
