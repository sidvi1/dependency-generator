package ru.sidvi.depextractor.processors;

import ru.sidvi.depextractor.extractors.JarInfo;

import java.util.List;

/**
 * ��������� ���������� �� ��������� ����������.
 */
public interface Processor {
    List<JarInfo> getInfos();

    Processor extract();
}
