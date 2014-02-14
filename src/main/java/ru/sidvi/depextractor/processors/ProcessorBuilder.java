package ru.sidvi.depextractor.processors;

import ru.sidvi.depextractor.extractors.Extractor;
import ru.sidvi.depextractor.pathcomparators.PathComparator;

/**
 * Created by sidvi on 14.02.14.
 */
public interface ProcessorBuilder {

    public ProcessorBuilder setPath(String path) ;

    JarProcessor build() ;
}
