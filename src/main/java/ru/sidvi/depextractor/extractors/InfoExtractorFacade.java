package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.pathcomparators.PathComparator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Извлекает из Jar файла информацию.
 */
public class InfoExtractorFacade {
    private String jarFile = "";

    public InfoExtractorFacade(String jarFile) {
        this.jarFile = jarFile;
    }

    public List<JarInfo> extract() {
        JarFile jar = null;
        try {
            jar = new JarFile(jarFile);
        } catch (IOException ignored) {
            //TODO:
        }
        if (jar == null) {
            return new ArrayList<JarInfo>();
        }

        List<JarInfo> result = new ArrayList<JarInfo>();
        Enumeration en = jar.entries();
        while (en.hasMoreElements()) {
            JarEntry file = (JarEntry) en.nextElement();

            for (PathComparator comparator : ExtractorsFactory.getRegisteredComparators()) {
                if (comparator.isValid(file.getName())) {
                    try {
                        InputStream is = jar.getInputStream(file);
                        result.addAll(ExtractorsFactory.get(comparator).extract(is));
                        is.close();
                    } catch (IOException ignored) {
                        //TODO:
                    }
                }
            }
        }
        for (JarInfo i : result) {
            i.setFileName(new File(jarFile).getName());
        }
        return  result;
    }

}
