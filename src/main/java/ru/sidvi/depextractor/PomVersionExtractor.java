package ru.sidvi.depextractor;

import java.io.InputStream;

/**
 * Created by sidvi on 04.02.14.
 */
public class PomVersionExtractor implements VersionExtractor {
    private InputStream is;

    public PomVersionExtractor(InputStream is) {
        this.is = is;
    }

    @Override
    public Version extract() {
        PomParser pomParser = new PomParser(is).parse();
        String version = pomParser.getVersion();

        return new Version(Version.Type.MAVEN_POM_VERSION, version);
    }

}
