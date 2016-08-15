package ru.sidvi.depextractor;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Ignore //TODO: fix for build on Travis-ci
public class TestMain {
    @Test
    public void integrationTest1() throws IOException {
        String jarsPath = getClass().getClassLoader().getResource("jars").getPath();
        String[] args = new String[]{jarsPath};

        Main main = new Main();
        String actual = main.doJob(args);

        assertThat(actual, is(string("fixtures/program_out.txt")));
    }

    private String string(String s) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(s));
    }
}
