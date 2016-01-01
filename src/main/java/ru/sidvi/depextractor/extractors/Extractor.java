package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.List;

/**
 * Извлекает информацию из заданного источника.
 */
public interface Extractor {

    List<JarInfo> extract(InputStream is);

}
