package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaly A. Sidorov on 07.02.14.
 */
class ParentPomExtractor implements Extractor {

    private PomParser parser;
    private List<JarInfo> infos = new ArrayList<JarInfo>();

    public ParentPomExtractor(PomParser parser) {
        this.parser = parser;
    }

    public List<JarInfo> extract(InputStream is) {
        parser.parse(is);
        infos.add(new JarInfo.Builder()
                .setGroup(parser.getParentGroupId(), PomSourceTypeDecorator.POM_XML_PARENT)
                .setArtifact(parser.getParentArtifactId(), PomSourceTypeDecorator.POM_XML_PARENT)
                .setVersion(parser.getParentVersion(), PomSourceTypeDecorator.POM_XML_PARENT)
                .build()
        );
        return infos;
    }

}
