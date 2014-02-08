package ru.sidvi.depextractor.processors;

import ru.sidvi.depextractor.JarInfo;

import java.util.List;

/**
 * Created by sidvi on 08.02.14.
 */
public interface Processor {
    List<JarInfo> getInfos();

    Processor extract();
}
