package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.pathcomparators.ManifestPathComparator;
import ru.sidvi.depextractor.extractors.pathcomparators.PathComparator;
import ru.sidvi.depextractor.extractors.pathcomparators.PomPathComparator;
import ru.sidvi.depextractor.extractors.pom.PomParser;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Создаем extractor на основе пути файла.
 */
public abstract class ExtractorsFactory {
    private static final List<PathComparator> COMPARATORS = new ArrayList<PathComparator>();

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

        @Override
        public List<JarInfo> extract(InputStream is) {
            return new ArrayList<JarInfo>();
        }
    }
}
