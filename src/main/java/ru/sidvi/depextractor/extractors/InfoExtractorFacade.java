package ru.sidvi.depextractor.extractors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sidvi.depextractor.extractors.pathcomparators.PathComparator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Извлекает из Jar файла информацию.
 */
public class InfoExtractorFacade {

    private Logger logger = LoggerFactory.getLogger(InfoExtractorFacade.class);

    private String jarFile = "";

    public InfoExtractorFacade(String jarFile) {
        this.jarFile = jarFile;
    }

    public List<JarInfo> extract() {
        Jar jar = loadJar();

        List<JarInfo> result = new ArrayList<>();
        Enumeration en = jar.entries();
        while (en.hasMoreElements()) {
            JarEntry file = (JarEntry) en.nextElement();

            for (PathComparator comparator : ExtractorsFactory.getRegisteredComparators()) {
                if (comparator.isValid(file.getName())) {
                    try (InputStream is = jar.getInputStream(file)) {
                        result.addAll(ExtractorsFactory.get(comparator).extract(is));
                    } catch (Exception ignored) {
                        logger.error("", ignored);
                    }
                }
            }
        }
        for (JarInfo i : result) {
            i.setFileName(new File(jarFile).getName());
        }
        return result;
    }

    private Jar loadJar() {
        Jar jar = new NullJarWrapper();
        try {
            jar = new JarWrapper(new JarFile(jarFile));
        } catch (IOException e) {
            logger.error("Error while openning file {}.", jar.getName(), e);
        }
        return jar;
    }

    private interface Jar {
        InputStream getInputStream(ZipEntry var1) throws IOException;

        Enumeration<JarEntry> entries();

        String getName();
    }

    private class JarWrapper implements Jar {
        private JarFile jar;

        public JarWrapper(JarFile jar) {
            this.jar = jar;
        }

        @Override
        public InputStream getInputStream(ZipEntry var1) throws IOException {
            return jar.getInputStream(var1);
        }

        @Override
        public Enumeration<JarEntry> entries() {
            return jar.entries();
        }

        @Override
        public String getName() {
            return jar.getName();
        }
    }

    private class NullJarWrapper implements Jar {

        @Override
        public InputStream getInputStream(ZipEntry var1) throws IOException {
            return null;
        }

        @Override
        public Enumeration<JarEntry> entries() {
            return new Enumeration<JarEntry>() {
                @Override
                public boolean hasMoreElements() {
                    return false;
                }

                @Override
                public JarEntry nextElement() {
                    return null;
                }
            };
        }

        @Override
        public String getName() {
            return "Null jar file.";
        }
    }
}
