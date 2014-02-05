package ru.sidvi.depextractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
* Created by sidvi on 05.02.14.
*/
class JarProcessor {
    private Info info = new Info();
    private String jarFile;

    public JarProcessor(String jarFile) {
        this.jarFile = jarFile;
    }

    public Info getInfo() {
        return info;
    }

    public JarProcessor extract() {
        try {
            JarFile jar = new JarFile(jarFile);
            Enumeration en = jar.entries();
            while (en.hasMoreElements()) {
                JarEntry file = (JarEntry) en.nextElement();
                if (file.getName().startsWith("META-INF") && file.getName().endsWith("pom.xml")) {
                    InputStream is = jar.getInputStream(file);
                    info.setVersion(new PomVersionExtractor(is).extract());
                    is.close();

                    is = jar.getInputStream(file);
                    info.setArtifact(new PomArtifactExtractor(is).extract());
                    is.close();
                }
            }
        } catch (IOException ignored) {
        }

        return this;
    }
}
