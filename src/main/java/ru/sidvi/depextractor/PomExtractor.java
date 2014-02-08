package ru.sidvi.depextractor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidvi on 07.02.14.
 */
public class PomExtractor implements Extractor {

    private PomParser parser;
    private List<BaseInfo> infos = new ArrayList<BaseInfo>();

    public void extract(InputStream is) {
        parser = new PomParser().parse(is);
        getParentInfo();
        getInfo();
    }

    private void getParentInfo() {
        infos.add(new Info(
                new Group(parser.getParentGroupId(), PomSource.POM_XML_PARENT),
                new Artifact(parser.getParentArtifactId(), PomSource.POM_XML_PARENT),
                new Version(parser.getParentVersion(), PomSource.POM_XML_PARENT)
        ));
    }

    private void getInfo() {
        infos.add(new Info(
                new Group(parser.getGroupId(), PomSource.POM_XML),
                new Artifact(parser.getArtifactId(), PomSource.POM_XML),
                new Version(parser.getVersion(), PomSource.POM_XML)
        ));
    }

    @Override
    public List<BaseInfo> getInfos() {
        return infos;
    }
}
