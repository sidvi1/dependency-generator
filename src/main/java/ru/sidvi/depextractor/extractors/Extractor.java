package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.JarInfo;

import java.io.InputStream;
import java.util.List;

/**
 * Created by sidvi on 07.02.14.
 */
public interface Extractor {
    void extract(InputStream is);

    List<JarInfo> getInfos();
}
