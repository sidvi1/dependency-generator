package ru.sidvi.depextractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by sidvi on 05.02.14.
 */
class JarProcessor {
    private List<Info> info = new ArrayList<Info>();
    private String jarFile;

    public JarProcessor(String jarFile) {
        this.jarFile = jarFile;
    }

    public List<Info> getInfos() {
        return info;
    }

    public JarProcessor extract() {

        JarFile jar = openJar();
        if (jar == null) {
            return this;
        }

        Enumeration en = jar.entries();
        while (en.hasMoreElements()) {
            JarEntry file = (JarEntry) en.nextElement();
            tryToExtractFromPom(jar, file);
            tryToExtractFromManifest(jar, file);
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
        for (Info i : info) {
            i.setFileName(new File(jarFile).getName());
        }
    }

    private void tryToExtractFromPom(JarFile jar, JarEntry file) {
        if (isMetainfDir(file) && isPomXml(file)) {
            tryToExtract(jar, file, new PomExtractor());
        }
    }

    private void tryToExtractFromManifest(JarFile jar, JarEntry file) {
        if (isMetainfDir(file) && isManifestMf(file)) {
            tryToExtract(jar, file, new ManifestExtractor());
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

    private boolean isPomXml(JarEntry file) {
        return file.getName().endsWith("pom.xml");
    }

    private boolean isMetainfDir(JarEntry file) {
        return file.getName().startsWith("META-INF");
    }

    private boolean isManifestMf(JarEntry file) {
        return file.getName().toLowerCase().endsWith("manifest.mf".toLowerCase());
    }

}
