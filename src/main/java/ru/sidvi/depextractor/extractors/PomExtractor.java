package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.Info;
import ru.sidvi.depextractor.JarInfo;
import ru.sidvi.depextractor.PomSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidvi on 07.02.14.
 */
class PomExtractor implements Extractor {

    private PomParser parser;
    private List<JarInfo> infos = new ArrayList<JarInfo>();

    public PomExtractor(PomParser parser) {
        this.parser = parser;
    }

    public void extract(InputStream is) {
        parser.parse(is);
        getParentInfo();
        getInfo();
    }

    private void getParentInfo() {
        infos.add(new Info.Builder()
                .setGroup(parser.getParentGroupId(), PomSource.POM_XML_PARENT)
                .setArtifact(parser.getParentArtifactId(), PomSource.POM_XML_PARENT)
                .setVersion(parser.getParentVersion(), PomSource.POM_XML_PARENT)
                .build()
        );
    }

    private void getInfo() {
        infos.add(new Info.Builder()
                .setGroup(parser.getGroupId(), PomSource.POM_XML)
                .setArtifact(parser.getArtifactId(), PomSource.POM_XML)
                .setVersion(parser.getVersion(), PomSource.POM_XML)
                .build()
        );
    }

    @Override
    public List<JarInfo> getInfos() {
        return infos;
    }
}
