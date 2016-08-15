package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.pom.PomParser;
import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

/**
 * Извлекает информацию из pom.xml
 */
class PomExtractor extends BasePomExtractor {

    public PomExtractor(PomParser parser) {
        super(parser);
    }

    @Override
    protected JarInfo buildJarInfo() {
        return new JarInfo.Builder(PomSourceTypeDecorator.POM_XML)
                .setGroup(parser.getGroupId())
                .setArtifact(parser.getArtifactId())
                .setVersion(parser.getVersion())
                .build();
    }
}

