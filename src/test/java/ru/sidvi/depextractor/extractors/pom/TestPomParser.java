package ru.sidvi.depextractor.extractors.pom;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPomParser {

    private InputStream is;


    @Test
    public void extractPartitionInfoFromParentTag() {
        is = getClass().getClassLoader().getResourceAsStream("files/sample2_pom.xml");

        PomParser parser = new PomParser();
        parser.parse(is);

        assertThat(parser.getParentGroupId(), is("org.slf4j"));
        assertThat(parser.getParentArtifactId(), is("slf4j-parent"));
        assertThat(parser.getParentVersion(), is("1.6.0"));
        assertThat(parser.getGroupId(), is(""));
        assertThat(parser.getArtifactId(), is("slf4j-api"));
        assertThat(parser.getVersion(), is(""));
    }

    @Test
    public void extractFromRealPom() {
        is = getClass().getClassLoader().getResourceAsStream("files/sample_pom.xml");

        PomParser parser = new PomParser();
        parser.parse(is);

        assertThat(parser.getParentGroupId(), is(""));
        assertThat(parser.getParentArtifactId(), is(""));
        assertThat(parser.getParentVersion(), is(""));
        assertThat(parser.getGroupId(), is("org.log4j"));
        assertThat(parser.getArtifactId(), is("log4j"));
        assertThat(parser.getVersion(), is("1.2.16"));
    }

    @After
    public void clean() throws IOException {
        is.close();
    }

}
