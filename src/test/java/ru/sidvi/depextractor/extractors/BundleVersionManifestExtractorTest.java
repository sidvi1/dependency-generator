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


public class BundleVersionManifestExtractorTest {
    private InputStream input;
    private List<JarInfo> expected = new ArrayList<>();

    @Test
    public void exctractFromLog4jMF() {
        input = getClass().getClassLoader().getResourceAsStream("files/js_MANIFEST.MF");
        Extractor e = new BundlelVersionMainifestExtractor();

        List<JarInfo> actual = e.extract(input);

        expected.add(new JarInfo.Builder(ManifestSourceTypeDecorator.MF_BUNDLE_VERSION)
                .setVersion("1.7.5.v201504281450")
                .build());
        assertEquals(expected, actual);
    }

    @After
    public void close() throws IOException {
        input.close();
        expected.clear();
    }
}
