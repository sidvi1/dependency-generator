package ru.sidvi.depextractor.extractors.pom;

/**
 * Хранит значение тэга который необходимы извлечь из XML.
 */
interface Tag {
    void checkForStart(String name, LevelHolder level);

    boolean isStarted();

    void checkForEnd(String tagName, LevelHolder level);

    void assignIfStarted(String data);

    String getName();

    String getValue();
}
