package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaly A. Sidorov on 07.02.14.
 */
class PomExtractor implements Extractor {

    private PomParser parser;
    private List<JarInfo> infos = new ArrayList<JarInfo>();

    public PomExtractor(PomParser parser) {
        this.parser = parser;
    }

    public List<JarInfo> extract(InputStream is) {
        parser.parse(is);
        infos.add(new Info.Builder()
                .setGroup(parser.getGroupId(), PomSourceTypeDecorator.POM_XML)
                .setArtifact(parser.getArtifactId(), PomSourceTypeDecorator.POM_XML)
                .setVersion(parser.getVersion(), PomSourceTypeDecorator.POM_XML)
                .build()
        );
        return infos;
    }

}
