package ru.sidvi.depextractor;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sidvi on 04.02.14.
 */
public class ProcessJarsTest {

    private static InputStream is;
    private final String path = getClass().getProtectionDomain().getCodeSource().getLocation().getFile();

    @After
    public void closeResources() throws IOException {
        if (is != null) {
            is.close();
        }
    }

    @Test
    public void testAll() throws IOException {
        String actual = Main.processDirectory(path);
        assertThat(actual, equalTo(asString("formatted_all.txt")));
    }

    @Test
    public void testJar() throws IOException {
        String jarFile = path + "/slf4j-api.jar";

        JarProcessor processJar = new JarProcessor(jarFile).extract();
        Info info = processJar.getInfo();

        assertThat(info, equalTo(new Info(new Version(Version.Type.MAVEN_POM_VERSION, "1.6.0"),
                new Artifact("org.slf4j", "slf4j-api"))));
    }

    @Test
    public void testFormatter() throws IOException {

        Info info = new Info(new Version(Version.Type.MAVEN_POM_VERSION, "1.6.0"),
                new Artifact("org.slf4j", "slf4j-api"));

        InfoFormatter formatter = new MavenFormatter();
        String actual = formatter.format(info);

        assertThat(actual, equalTo(asString("formatted1.txt")));
    }

    private String asString(String s) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(s));
    }

}



