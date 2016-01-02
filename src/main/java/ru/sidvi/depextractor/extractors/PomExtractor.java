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

    public List<JarInfo> extract(InputStream is) {
        List<JarInfo> infos = new ArrayList<JarInfo>();
        parser.parse(is);
        JarInfo extracted = new JarInfo.Builder()
                .setGroup(parser.getGroupId(), PomSourceTypeDecorator.POM_XML)
                .setArtifact(parser.getArtifactId(), PomSourceTypeDecorator.POM_XML)
                .setVersion(parser.getVersion(), PomSourceTypeDecorator.POM_XML)
                .build();
        if(extracted.getFormattedResult("%s%s%s").trim().length() > 0){
            infos.add(extracted);
        }
        return infos;
    }

}
