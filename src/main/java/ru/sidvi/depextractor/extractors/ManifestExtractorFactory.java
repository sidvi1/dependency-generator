package ru.sidvi.depextractor.extractors;

/**
 * Created by sidvi on 14.02.14.
 */
public class ManifestExtractorFactory implements ExtractorFactory{

    public Extractor create(){
        return new ManifestExtractor();
    }
}
