package ru.sidvi.depextractor;

import java.io.InputStream;

/**
 * Created by sidvi on 04.02.14.
 */
public class PomArtifactExtractor implements ArtifactExtractor {
    private InputStream is;

    public PomArtifactExtractor(InputStream is) {
        this.is = is;
    }

    @Override
    public Artifact extract() {
        PomParser parser = new PomParser(is);
        parser.parse();
        return new Artifact(parser.getGroupId(), parser.getArtifactId());
    }
}
