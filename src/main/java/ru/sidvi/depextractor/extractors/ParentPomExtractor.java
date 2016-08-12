package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.pom.PomParser;
import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Извлекает информацию из секции parent pom.xml.
 */
class ParentPomExtractor implements Extractor {

    private PomParser parser;

    public ParentPomExtractor(PomParser parser) {
        this.parser = parser;
    }

    @Override
    public List<JarInfo> extract(InputStream is) {
        List<JarInfo> infos = new ArrayList<JarInfo>();
        parser.parse(is);
        JarInfo extracted = new JarInfo.Builder()
                .setGroup(parser.getParentGroupId(), PomSourceTypeDecorator.POM_XML_PARENT)
                .setArtifact(parser.getParentArtifactId(), PomSourceTypeDecorator.POM_XML_PARENT)
                .setVersion(parser.getParentVersion(), PomSourceTypeDecorator.POM_XML_PARENT)
                .build();
        if (extracted.getFormattedResult("%s%s%s").trim().length() > 0) {
            infos.add(extracted);
        }
        return infos;
    }

}
