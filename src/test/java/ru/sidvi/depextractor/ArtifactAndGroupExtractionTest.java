package ru.sidvi.depextractor;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sidvi on 04.02.14.
 */
public class ArtifactAndGroupExtractionTest {

    private static InputStream is;

    @After
    public void closeResources() throws IOException {
        is.close();
    }

    @Test
    public void testExtractArtifactAndGroupFromPom() {
        is = getClass().getClassLoader().getResourceAsStream("sample_pom.xml");

        ArtifactExtractor extractor = new PomArtifactExtractor(is);
        Artifact actual = extractor.extract();
        assertThat(actual, equalTo(new Artifact("org.log4j", "log4j")));
    }
}
