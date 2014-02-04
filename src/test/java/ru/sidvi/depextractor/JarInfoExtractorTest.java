package ru.sidvi.depextractor;

import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sidvi on 04.02.14.
 */
public class JarInfoExtractorTest {

    private static InputStream is;

    @After
    public void closeResources() throws IOException {
        is.close();
    }

    @Test
    public void testSpecificationInfo() throws FileNotFoundException {
        String resource = "lucene_MANIFEST.MF";
        Version expectedVersion = new Version(Version.Type.MANIFEST_SPEC_VERSION, "4.4.0");

        is = getClass().getClassLoader().getResourceAsStream(resource);
        assertThat(new ManifestExtractor(is).extract(), equalTo(expectedVersion));
    }

    @Test
    public void testImplementationInfo() throws FileNotFoundException {
        String resource = "log4j_MANIFEST.MF";
        Version expectedVersion = new Version(Version.Type.MANIFEST_IMPL_VERSION, "1.2.16");

        is = getClass().getClassLoader().getResourceAsStream(resource);
        assertThat(new ManifestExtractor(is).extract(), equalTo(expectedVersion));
    }

    @Test
    public void testExtractVersionFromPom() {
        String resource = "sample_pom.xml";
        Version expectedVersion = new Version(Version.Type.MAVEN_POM_VERSION, "1.2.16");

        is = getClass().getClassLoader().getResourceAsStream(resource);
        assertThat(new PomExtractor(is).extract(), equalTo(expectedVersion));
    }

    @Test
    public void testExtractArtifactAndGroupFromPom() {
        is = getClass().getClassLoader().getResourceAsStream("sample_pom.xml");
        PomParser parser = new PomParser(is);
        parser.parse();

        assertThat(parser.getGroupId(), equalTo("org.log4j"));
        assertThat(parser.getArtifactId(), equalTo("log4j"));
    }

}
