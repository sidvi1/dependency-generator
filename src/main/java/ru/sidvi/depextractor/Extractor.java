package ru.sidvi.depextractor;

import java.io.InputStream;
import java.util.List;

/**
 * Created by sidvi on 07.02.14.
 */
public interface Extractor {
    void extract(InputStream is);
    List<BaseInfo> getInfos();
}
