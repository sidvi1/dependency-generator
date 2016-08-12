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


public class TestManifestExtractor {
    private InputStream input;
    private List<JarInfo> expected = new ArrayList<>();

    @Test
    public void exctractFromLog4jMF() {
        input = getClass().getClassLoader().getResourceAsStream("files/log4j_MANIFEST.MF");
        Extractor e = new ManifestExtractor();
        List<JarInfo> actual = e.extract(input);

        expected.add(new JarInfo.Builder()
                .setVersion("1.2.16", ManifestSourceTypeDecorator.MF_IMPLEMENTATION_VERSION)
                .build());
        assertEquals(actual, expected);
    }

    @Test
    public void extractFromLucene() {
        input = getClass().getClassLoader().getResourceAsStream("files/lucene_MANIFEST.MF");
        Extractor e = new ManifestExtractor();
        List<JarInfo> actual = e.extract(input);

        expected.add(new JarInfo.Builder()
                .setVersion("4.4.0", ManifestSourceTypeDecorator.MF_SPECIFICATION_VERSION)
                .build());
        expected.add(new JarInfo.Builder()
                .setVersion("4.4.0 1504776 - sarowe - 2013-07-19 02", ManifestSourceTypeDecorator.MF_IMPLEMENTATION_VERSION)
                .build());
        assertEquals(actual, expected);
    }

    @After
    public void close() throws IOException {
        input.close();
        expected.clear();
    }
}