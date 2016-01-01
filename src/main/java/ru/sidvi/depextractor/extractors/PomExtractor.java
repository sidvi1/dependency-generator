package ru.sidvi.depextractor.extractors;

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
    private List<JarInfo> infos = new ArrayList<>();

    public PomExtractor(PomParser parser) {
        this.parser = parser;
    }

    public List<JarInfo> extract(InputStream is) {
        parser.parse(is);
        infos.add(new JarInfo.Builder()
                .setGroup(parser.getGroupId(), PomSourceTypeDecorator.POM_XML)
                .setArtifact(parser.getArtifactId(), PomSourceTypeDecorator.POM_XML)
                .setVersion(parser.getVersion(), PomSourceTypeDecorator.POM_XML)
                .build()
        );
        return infos;
    }

}
