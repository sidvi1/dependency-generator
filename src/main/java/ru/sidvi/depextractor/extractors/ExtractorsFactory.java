package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.model.Info;
import ru.sidvi.depextractor.pathcomparators.ManifestPathComparator;
import ru.sidvi.depextractor.pathcomparators.PathComparator;
import ru.sidvi.depextractor.pathcomparators.PomPathComparator;

import java.io.InputStream;
import java.util.*;

/**
 * Создаем extractor на основе пути файла.
 */
public abstract class ExtractorsFactory {
    private final static List<PathComparator> COMPARATORS = new ArrayList<PathComparator>();

    static {
        COMPARATORS.add(new PomPathComparator());
        COMPARATORS.add(new ManifestPathComparator());
    }

    private ExtractorsFactory() {
    }

    public static Extractor get(PathComparator comparator) {
        if (comparator instanceof PomPathComparator) {
            return new CompoundExtractor(new Extractor[]{new ParentPomExtractor(new PomParser()), new PomExtractor(new PomParser())});
        }
        if (comparator instanceof ManifestPathComparator) {
            return new ManifestExtractor();
        }
        return new NoneExtractor();
    }

    public static List<PathComparator> getRegisteredComparators() {
        return COMPARATORS;
    }

    private static class NoneExtractor implements Extractor {
        public List<Info> extract(InputStream is) {
            return new ArrayList<Info>();
        }
    }
}
