package ru.sidvi.depextractor;

import java.io.InputStream;

/**
 * Created by sidvi on 04.02.14.
 */
public class PomExtractor implements InfoExtractor {
    private InputStream is;

    public PomExtractor(InputStream is) {
        this.is = is;
    }

    @Override
    public Info extract() {
        PomParser pomParser = new PomParser(is).parse();
        String version = pomParser.getVersion();

        return new Info("", new Info.Version(Info.Version.Type.MAVEN_POM_VERSION, version));
    }

}
