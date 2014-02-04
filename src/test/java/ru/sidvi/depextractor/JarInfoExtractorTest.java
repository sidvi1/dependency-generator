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
        is = getClass().getClassLoader().getResourceAsStream("lucene_MANIFEST.MF");
        InfoExtractor extractor = new ManifestExtractor(is);
        Info info = extractor.extract();

        assertThat(info, equalTo(new Info("", new Info.Version(Info.Version.Type.MANIFEST_SPEC_VERSION, "4.4.0"))));
    }

    @Test
    public void testImplementationInfo() throws FileNotFoundException {
        is = getClass().getClassLoader().getResourceAsStream("log4j_MANIFEST.MF");
        InfoExtractor extractor = new ManifestExtractor(is);
        Info info = extractor.extract();

        assertThat(info, equalTo(new Info("", new Info.Version(Info.Version.Type.MANIFEST_IMPL_VERSION, "1.2.16"))));
    }

    @Test
    public void testExtractVersionFromPom() {
        is = getClass().getClassLoader().getResourceAsStream("sample_pom.xml");
        InfoExtractor extractor = new PomExtractor(is);
        Info info = extractor.extract();

        assertThat(info, equalTo(new Info("", new Info.Version(Info.Version.Type.MAVEN_POM_VERSION, "1.2.16"))));
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
