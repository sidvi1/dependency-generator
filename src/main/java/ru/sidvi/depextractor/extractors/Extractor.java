package ru.sidvi.depextractor.extractors;

import java.io.InputStream;
import java.util.List;

/**
 * ��������� ���������� �� ��������� ���������.
 */
public interface Extractor {

    List<JarInfo> extract(InputStream is);

}
