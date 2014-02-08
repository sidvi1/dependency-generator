package ru.sidvi.depextractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by sidvi on 05.02.14.
 */
class JarProcessor {

    private List<BaseInfo> info = new ArrayList<BaseInfo>();
    private String jarFile;
    private Map<PathComparator, Extractor> extractors = new HashMap<PathComparator, Extractor>();

    public JarProcessor(String jarFile) {
        this.jarFile = jarFile;
    }

    public List<BaseInfo> getInfos() {
        return info;
    }

    public void addExtractor(PathComparator comparator, Extractor extractor){
        extractors.put(comparator,extractor);
    }

    public JarProcessor extract() {

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
        for (BaseInfo i : info) {
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

}
