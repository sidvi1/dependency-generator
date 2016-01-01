package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.Info;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaly A. Sidorov on 07.02.14.
 */
class ParentPomExtractor implements Extractor {

    private PomParser parser;
    private List<Info> infos = new ArrayList<Info>();

    public ParentPomExtractor(PomParser parser) {
        this.parser = parser;
    }

    public List<Info> extract(InputStream is) {
        parser.parse(is);
        infos.add(new Info.Builder()
                .setGroup(parser.getParentGroupId(), PomSourceTypeDecorator.POM_XML_PARENT)
                .setArtifact(parser.getParentArtifactId(), PomSourceTypeDecorator.POM_XML_PARENT)
                .setVersion(parser.getParentVersion(), PomSourceTypeDecorator.POM_XML_PARENT)
                .build()
        );
        return infos;
    }

}
