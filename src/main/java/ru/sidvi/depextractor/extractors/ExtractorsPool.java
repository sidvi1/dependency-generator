package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.pathcomparators.ManifestPathComparator;
import ru.sidvi.depextractor.pathcomparators.PathComparator;
import ru.sidvi.depextractor.pathcomparators.PomPathComparator;

import java.util.*;

/**
 * Создаем extractor на основе пути файла.
 */
public abstract class ExtractorsPool {
    private final static Map<PathComparator, Extractor> EXTRACTORS = new HashMap<PathComparator, Extractor>();

    static {
        EXTRACTORS.put(new PomPathComparator(), new PomExtractor(new PomParser()));
        EXTRACTORS.put(new ManifestPathComparator(), new ManifestExtractor());
    }

    private ExtractorsPool() {
    }

    public static Extractor get(PathComparator comparator) {
        return EXTRACTORS.get(comparator);
    }

    public static List<PathComparator> getRegisteredComparators() {
        ArrayList<PathComparator> list = new ArrayList<PathComparator>();
        list.addAll(EXTRACTORS.keySet());
        return list;
    }
}
