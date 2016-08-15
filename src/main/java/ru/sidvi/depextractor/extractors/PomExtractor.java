package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.pom.PomParser;
import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Извлекает информацию из pom.xml
 */
class PomExtractor implements Extractor {

    private PomParser parser;

    public PomExtractor(PomParser parser) {
        this.parser = parser;
    }

    @Override
    public List<JarInfo> extract(InputStream is) {
        List<JarInfo> infos = new ArrayList<JarInfo>();
        parser.parse(is);
        JarInfo extracted = new JarInfo.Builder(PomSourceTypeDecorator.POM_XML)
                .setGroup(parser.getGroupId())
                .setArtifact(parser.getArtifactId())
                .setVersion(parser.getVersion())
                .build();
        if (extracted.getFormattedResult("%s%s%s").trim().length() > 0) {
            infos.add(extracted);
        }
        return infos;
    }

}
