package ru.sidvi.depextractor;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sidvi on 07.02.14.
 */
public class MainMethodsTest {

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
        assertThat(actual, equalTo(asString("line_out.txt")));
    }

    private String asString(String s) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(s));
    }

}
