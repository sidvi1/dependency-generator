package ru.sidvi.depextractor.extractors;

import org.apache.commons.io.IOUtils;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Вызывает несколько Comparator на одном источнике.
 * Источник перед этим считывается в буфер.
 */
class CompoundExtractor implements Extractor {
    private final List<Extractor> extractors;
    private String casched = "";


    public CompoundExtractor(Extractor[] extractors) {
        this.extractors = Arrays.asList(extractors);
    }

    @Override
    public List<JarInfo> extract(InputStream is) {
        createCache(is);

        List<JarInfo> results = new ArrayList<>();
        for (Extractor extractor : extractors) {
            results.addAll(extractor.extract(getCached()));
        }
        return results;
    }

    private ByteArrayInputStream getCached() {
        byte[] bytes = {};
        try {
            bytes = casched.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return new ByteArrayInputStream(bytes);
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
