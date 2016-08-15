package ru.sidvi.depextractor.extractors;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Вызывает несколько Comparator на одном источнике.
 * Источник перед этим считывается в буфер.
 */
class CompoundExtractor implements Extractor {

    private List<Extractor> extractors = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(CompoundExtractor.class);
    private String casched = "";

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
            logger.error("Can't get cached data.", e);
        }
        return new ByteArrayInputStream(bytes);
    }

    private void createCache(InputStream is) {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(is, writer, "UTF-8");
        } catch (IOException e) {
            logger.error("Can't copy data.", e);
        }
        casched = writer.getBuffer().toString();
    }

    public CompoundExtractor add(Extractor extractor) {
        extractors.add(extractor);
        return this;
    }
}
