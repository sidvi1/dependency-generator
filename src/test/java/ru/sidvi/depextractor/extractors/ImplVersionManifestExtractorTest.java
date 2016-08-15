package ru.sidvi.depextractor.extractors;

import org.junit.After;
import org.junit.Test;
import ru.sidvi.depextractor.extractors.sourcetypes.ManifestSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ImplVersionManifestExtractorTest {
    private InputStream input;
    private List<JarInfo> expected = new ArrayList<>();

    @Test
    public void exctractFromLog4jMF() {
        input = getClass().getClassLoader().getResourceAsStream("files/log4j_MANIFEST.MF");
        Extractor e = new ImpllVersionMainifestExtractor();
        List<JarInfo> actual = e.extract(input);

        expected.add(new JarInfo.Builder(ManifestSourceTypeDecorator.MF_IMPLEMENTATION_VERSION)
                .setVersion("1.2.16")
                .build());
        assertEquals(actual, expected);
    }

    @Test
    public void extractFromLucene() {
        input = getClass().getClassLoader().getResourceAsStream("files/lucene_MANIFEST.MF");
        Extractor e = new ImpllVersionMainifestExtractor();
        List<JarInfo> actual = e.extract(input);

        expected.add(new JarInfo.Builder(ManifestSourceTypeDecorator.MF_IMPLEMENTATION_VERSION)
                .setVersion("4.4.0 1504776 - sarowe - 2013-07-19 02")
                .build());
        assertEquals(expected, actual);
    }

    @After
    public void close() throws IOException {
        input.close();
        expected.clear();
    }
}
