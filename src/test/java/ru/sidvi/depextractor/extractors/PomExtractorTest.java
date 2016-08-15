package ru.sidvi.depextractor.extractors;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Before;
import org.junit.Test;
import ru.sidvi.depextractor.extractors.pom.PomParser;
import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
public class PomExtractorTest {

    private PomExtractor extractor;

    private InputStream is;

    @Before
    public void setUp() {
        PomParser parser = new PomParser();
        extractor = new PomExtractor(parser);
    }

    @Test
    public void shouldExtractPomInfo() {
        is = getClass().getClassLoader().getResourceAsStream("files/sample_pom.xml");

        List<JarInfo> actual = extractor.extract(is);

        assertEquals(1, actual.size());
        assertThat(actual, IsCollectionContaining.hasItem(
                new JarInfo.Builder(PomSourceTypeDecorator.POM_XML)
                        .setGroup("org.log4j")
                        .setArtifact("log4j")
                        .setVersion("1.2.16")
                        .build()));
    }

   @Test
    public void shouldNotExtract() {
        is = getClass().getClassLoader().getResourceAsStream("files/sample3_pom.xml");

        List<JarInfo> actual = extractor.extract(is);

        assertEquals(0, actual.size());
    }

}