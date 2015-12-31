package ru.sidvi.depextractor.extractors;

import java.io.InputStream;
import java.util.List;

/**
 * ��������� ���������� �� ��������� ���������.
 */
public interface Extractor {
    void extract(InputStream is);

    List<JarInfo> getInfos();
}
