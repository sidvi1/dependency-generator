package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.sourcetypes.ManifestSourceTypeDecorator;
import ru.sidvi.depextractor.model.Info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaly A. Sidorov on 04.02.14.
 */
class ManifestExtractor implements Extractor {

    public static final String IMPLEMENTATION_VERSION = "Implementation-Version";
    public static final String SPECIFICATION_VERSION = "Specification-Version";
    private List<Info> infos = new ArrayList<Info>();

    public ManifestExtractor() {
    }

    public List<Info> extract(InputStream is) {
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

            Info info = new Info.Builder()
                    .setVersion(split[1].trim(), source)
                    .build();
            infos.add(info);
        }
    }
}
