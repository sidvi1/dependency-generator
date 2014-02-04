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
public class VersionExtractionTest {

    private static InputStream is;

    @After
    public void closeResources() throws IOException {
        if(is != null){
            is.close();
        }
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
        assertThat(new PomVersionExtractor(is).extract(), equalTo(expectedVersion));
    }

    @Test
    public void testExtractVersionFromPomParent() {
        String resource = "sample2_pom.xml";
        Version expectedVersion = new Version(Version.Type.MAVEN_POM_VERSION, "1.6.0");

        is = getClass().getClassLoader().getResourceAsStream(resource);
        assertThat(new PomVersionExtractor(is).extract(), equalTo(expectedVersion));
    }

    @Test
    public void testExtractArtifactFromPomParent() {
        String resource = "sample2_pom.xml";
        Artifact expected = new Artifact("org.slf4j","slf4j-api");

        is = getClass().getClassLoader().getResourceAsStream(resource);
        assertThat(new PomArtifactExtractor(is).extract(), equalTo(expected));
    }



}
