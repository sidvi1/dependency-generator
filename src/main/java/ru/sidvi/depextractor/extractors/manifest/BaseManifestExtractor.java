package ru.sidvi.depextractor.extractors.manifest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sidvi.depextractor.extractors.Extractor;
import ru.sidvi.depextractor.extractors.sourcetypes.ManifestSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
public abstract class BaseManifestExtractor implements Extractor {

    private Logger logger = LoggerFactory.getLogger(BaseManifestExtractor.class);

    private List<JarInfo> infos = new ArrayList<JarInfo>();

    @Override
    public List<JarInfo> extract(InputStream is) {

        try (InputStreamReader streamReader = new InputStreamReader(is, "UTF-8")) {

            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = readLine(reader)) != null) {
                String[] split = line.split(":");
                parseLine(split);
            }
        } catch (IOException e) {
            logger.error("", e);

        }
        return infos;
    }

    private String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException ignored) {
            logger.error("Error while reading line.", reader);
        }
        return "";
    }

    protected abstract void parseLine(String[] split) ;

    protected void extractFieldValue(String[] split, String field, ManifestSourceTypeDecorator source) {
        if (split[0].trim().equals(field)) {

            JarInfo info = new JarInfo.Builder(source)
                    .setVersion(split[1].trim())
                    .build();
            infos.add(info);
        }
    }
}
