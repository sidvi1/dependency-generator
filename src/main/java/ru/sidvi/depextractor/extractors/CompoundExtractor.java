package ru.sidvi.depextractor.extractors;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Вызывает несколько Comparator на одном источнике.
 * Источник перед этим считывается в буфер.
 */
public class CompoundExtractor implements Extractor {
    private final List<Extractor> extractors;
    private String casched = "";


    public CompoundExtractor(Extractor[] extractors) {
        this.extractors = Arrays.asList(extractors);
    }

    public List<JarInfo> extract(InputStream is) {
        createCache(is);

        List<JarInfo> results = new ArrayList<>();
        for (Extractor extractor : extractors) {
            results.addAll(extractor.extract(getCached()));
        }
        return results;
    }

    private ByteArrayInputStream getCached() {
        return new ByteArrayInputStream(casched.getBytes());
    }

    private void createCache(InputStream is) {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(is, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        casched = writer.getBuffer().toString();
    }
}
