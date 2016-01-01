package ru.sidvi.depextractor.processors;

import ru.sidvi.depextractor.extractors.ExtractorsPool;
import ru.sidvi.depextractor.extractors.JarInfo;
import ru.sidvi.depextractor.extractors.Extractor;
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
public class InfoExtractorFacade {

    private List<JarInfo> info = new ArrayList<JarInfo>();
    private String jarFile = "";

    public InfoExtractorFacade(String jarFile) {
        this.jarFile = jarFile;
    }

    public List<JarInfo> getInfos() {
        return info;
    }

    public InfoExtractorFacade extract() {

        JarFile jar = openJar();
        if (jar == null) {
            return this;
        }

        Enumeration en = jar.entries();
        while (en.hasMoreElements()) {
            JarEntry file = (JarEntry) en.nextElement();

            for (PathComparator comparator : ExtractorsPool.getRegisteredComparators()) {
                if (comparator.isValid(file.getName())) {
                    tryToExtract(jar, file, ExtractorsPool.get(comparator));
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
}
