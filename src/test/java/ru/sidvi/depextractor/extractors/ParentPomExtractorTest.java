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
public class ParentPomExtractorTest {

    private ParentPomExtractor extractor;

    private InputStream is;

    @Before
    public void setUp() {
        PomParser parser = new PomParser();
        extractor = new ParentPomExtractor(parser);
    }

    @Test
    public void shouldExtractParentInfo() {
        is = getClass().getClassLoader().getResourceAsStream("files/sample2_pom.xml");

        List<JarInfo> actual = extractor.extract(is);

        assertEquals(1, actual.size());
        assertThat(actual, IsCollectionContaining.hasItem(
                new JarInfo.Builder(PomSourceTypeDecorator.POM_XML_PARENT)
                        .setGroup("org.slf4j")
                        .setArtifact("slf4j-parent")
                        .setVersion("1.6.0")
                        .build()));
    }

}