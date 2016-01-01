package ru.sidvi.depextractor.extractors.pom;

/**
 * Created by sidvi on 01.01.2016.
 */
interface Tag {
    void checkForStart(String name);

    boolean isStarted();

    void checkForEnd(String tagName);

    void assignIfStarted(String data);

    String getName();

    String getValue();
}
