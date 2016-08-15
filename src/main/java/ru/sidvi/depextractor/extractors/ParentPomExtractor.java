package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.pom.PomParser;
import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

/**
 * Извлекает информацию из секции parent pom.xml.
 */
class ParentPomExtractor extends BasePomExtractor {

    public ParentPomExtractor(PomParser parser) {
        super(parser);
    }

    @Override
    protected JarInfo buildJarInfo() {
        return new JarInfo.Builder(PomSourceTypeDecorator.POM_XML_PARENT)
                .setGroup(parser.getParentGroupId())
                .setArtifact(parser.getParentArtifactId())
                .setVersion(parser.getParentVersion())
                .build();
    }
}
