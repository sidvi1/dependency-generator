package ru.sidvi.depextractor.extractors;

/**
 * Created by Vitaly A. Sidorov on 14.02.14.
 */
public class PomExtractorFactory implements ExtractorFactory {

    public Extractor create() {
        return new PomExtractor(new PomParser());
    }
}
