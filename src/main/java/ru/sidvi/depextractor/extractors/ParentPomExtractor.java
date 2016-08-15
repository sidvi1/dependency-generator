package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.pom.PomParser;
import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.List;

/**
 * Извлекает информацию из секции parent pom.xml.
 */
class ParentPomExtractor extends BasePomExtractor {

    public ParentPomExtractor(PomParser parser) {
        super(parser);
    }

    @Override
    public List<JarInfo> extract(InputStream is) {
        return extract(is
                , parser.getParentGroupId()
                , parser.getParentArtifactId()
                , parser.getParentVersion()
                , PomSourceTypeDecorator.POM_XML_PARENT);
    }
}
