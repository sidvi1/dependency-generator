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
        JarFile jar = null;
        try {
            jar = new JarFile(jarFile);
        } catch (IOException e) {
            logger.error("Error while openning file {}.", jar.getName(), e);
        }

        List<JarInfo> result = new ArrayList<>();
        Enumeration en = jar.entries();
        while (en.hasMoreElements()) {
            JarEntry file = (JarEntry) en.nextElement();

            for (PathComparator comparator : ExtractorsFactory.getRegisteredComparators()) {
                if (comparator.isValid(file.getName())) {
                    InputStream is = null;
                    try {
                        is = jar.getInputStream(file);
                        result.addAll(ExtractorsFactory.get(comparator).extract(is));
                    } catch (Exception ignored) {
                        logger.error("", ignored);
                    } finally {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e) {
                                logger.error("", e);
                            }
                        }
                    }
                }
            }
        }
        for (JarInfo i : result) {
            i.setFileName(new File(jarFile).getName());
        }
        return result;
    }

}
