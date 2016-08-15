package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.pom.PomParser;
import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.List;

/**
 * Извлекает информацию из pom.xml
 */
class PomExtractor extends BasePomExtractor {

    public PomExtractor(PomParser parser) {
        super(parser);
    }

    @Override
    public List<JarInfo> extract(InputStream is) {
        return extract(is
                , parser.getGroupId()
                , parser.getArtifactId()
                , parser.getVersion()
                , PomSourceTypeDecorator.POM_XML);
    }
}
