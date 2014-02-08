package ru.sidvi.depextractor;

import java.util.List;

/**
 * Created by sidvi on 08.02.14.
 */
public interface Processor {
    List<JarInfo> getInfos();

    Processor extract();
}
