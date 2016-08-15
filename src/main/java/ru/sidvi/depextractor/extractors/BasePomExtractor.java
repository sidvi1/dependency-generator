package ru.sidvi.depextractor.extractors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sidvi.depextractor.extractors.pom.PomParser;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
public abstract class BasePomExtractor implements Extractor {
    private final Logger logger = LoggerFactory.getLogger(BasePomExtractor.class);
    protected PomParser parser;

    public BasePomExtractor(PomParser parser) {
        this.parser = parser;
    }

    @Override
    public List<JarInfo> extract(InputStream is) {
        List<JarInfo> infos = new ArrayList<>();
        parser.parse(is);
        JarInfo extracted = buildJarInfo();
        if (notEmpty(extracted)) {
            infos.add(extracted);
        }
        return infos;
    }

    protected abstract JarInfo buildJarInfo();

    private boolean notEmpty(JarInfo extracted) {
        return extracted.getFormattedResult("%s%s%s").trim().length() > 0;
    }
}
