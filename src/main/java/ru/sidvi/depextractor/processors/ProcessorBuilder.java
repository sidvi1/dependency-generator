package ru.sidvi.depextractor.processors;

/**
 * Created by Vitaly A. Sidorov on 14.02.14.
 */
public interface ProcessorBuilder {

    ProcessorBuilder setPath(String path);

    Processor build();
}
