package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.sourcetypes.ManifestSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Извлекает информацию из MANIFEST.MF
 */
class ManifestExtractor implements Extractor {

    public static final String IMPLEMENTATION_VERSION = "Implementation-Version";
    public static final String SPECIFICATION_VERSION = "Specification-Version";
    private List<JarInfo> infos = new ArrayList<JarInfo>();

    public ManifestExtractor() {
    }

    public List<JarInfo> extract(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = readLine(reader)) != null) {
            String[] split = line.split(":");
            parseSpecificationVersion(split);
            parseImplementationVersion(split);
        }
        return infos;
    }

    private String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException ignored) {
        }
        return null;
    }

    private void parseImplementationVersion(String[] split) {
        extractFieldValue(split, IMPLEMENTATION_VERSION, ManifestSourceTypeDecorator.MF_IMPLEMENTATION_VERSION);
    }

    private void parseSpecificationVersion(String[] split) {
        extractFieldValue(split, SPECIFICATION_VERSION, ManifestSourceTypeDecorator.MF_SPECIFICATION_VERSION);
    }

    private void extractFieldValue(String[] split, String field, ManifestSourceTypeDecorator source) {
        if (split[0].trim().equals(field)) {

            JarInfo info = new JarInfo.Builder()
                    .setVersion(split[1].trim(), source)
                    .build();
            infos.add(info);
        }
    }
}
