package ru.sidvi.depextractor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidvi on 07.02.14.
 */
public class PomExtractor implements Extractor {

    private PomParser parser;
    private List<JarInfo> infos = new ArrayList<JarInfo>();

    public void extract(InputStream is) {
        parser = new PomParser().parse(is);
        getParentInfo();
        getInfo();
    }

    private void getParentInfo() {
        infos.add(new Info(
                Info.create(parser.getParentGroupId(), PomSource.POM_XML_PARENT),
                Info.create(parser.getParentArtifactId(), PomSource.POM_XML_PARENT),
                Info.create(parser.getParentVersion(), PomSource.POM_XML_PARENT)
        ));
    }

    private void getInfo() {
        infos.add(new Info(
                Info.create(parser.getGroupId(), PomSource.POM_XML),
                Info.create(parser.getArtifactId(), PomSource.POM_XML),
                Info.create(parser.getVersion(), PomSource.POM_XML)
        ));
    }

    @Override
    public List<JarInfo> getInfos() {
        return infos;
    }
}
