package ru.sidvi.depextractor.processors;

import ru.sidvi.depextractor.JarInfo;
import ru.sidvi.depextractor.extractors.Extractor;
import ru.sidvi.depextractor.extractors.ExtractorFactory;
import ru.sidvi.depextractor.pathcomparators.PathComparator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Vitaly A. Sidorov on 05.02.14.
 */
public class JarProcessor implements Processor {

    private List<JarInfo> info = new ArrayList<JarInfo>();
    private String jarFile = "";
    private Map<PathComparator, Extractor> extractors = new HashMap<PathComparator, Extractor>();

    private JarProcessor(String jarFile) {
        this.jarFile = jarFile;
    }

    private JarProcessor(Builder builder) {
        this(builder.getAbsolutePath());
        extractors.putAll(builder.extractors);
    }

    public List<JarInfo> getInfos() {
        return info;
    }

    public Processor extract() {

        JarFile jar = openJar();
        if (jar == null) {
            return this;
        }

        Enumeration en = jar.entries();
        while (en.hasMoreElements()) {
            JarEntry file = (JarEntry) en.nextElement();

            for (PathComparator comparator : extractors.keySet()) {
                if (comparator.isValid(file.getName())) {
                    tryToExtract(jar, file, extractors.get(comparator));
                }
            }
        }
        applyFileName();
        return this;
    }

    private JarFile openJar() {
        JarFile jar = null;
        try {
            jar = new JarFile(jarFile);
        } catch (IOException ignored) {
        }
        return jar;
    }

    private void applyFileName() {
        for (JarInfo i : info) {
            i.setFileName(new File(jarFile).getName());
        }
    }

    private void tryToExtract(JarFile jar, JarEntry file, Extractor extractor) {
        try {
            extract(extractor, jar.getInputStream(file));
        } catch (IOException ignored) {
        }
    }

    private void extract(Extractor extractor, InputStream is) throws IOException {
        extractor.extract(is);
        is.close();
        info.addAll(extractor.getInfos());
    }

    static public class Builder implements ProcessorBuilder {

        private Map<PathComparator, ExtractorFactory> extractorFactories = new HashMap<PathComparator, ExtractorFactory>();
        private String absolutePath = "";
        private Map<PathComparator, Extractor> extractors = new HashMap<PathComparator, Extractor>();

        public String getAbsolutePath() {
            return absolutePath;
        }

        public Builder setPath(String path) {
            absolutePath = path;
            return this;
        }

        public Builder addExtractor(PathComparator comparator, ExtractorFactory extractor) {
            extractorFactories.put(comparator, extractor);
            return this;
        }

        public JarProcessor build() {
            for (Map.Entry<PathComparator, ExtractorFactory> entry : extractorFactories.entrySet()) {
                extractors.put(entry.getKey(), entry.getValue().create());
            }

            return new JarProcessor(this);
        }
    }
}
