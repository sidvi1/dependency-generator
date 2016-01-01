package ru.sidvi.depextractor.model;

import ru.sidvi.depextractor.extractors.sourcetypes.NoneSourceTypeDecorator;
import ru.sidvi.depextractor.extractors.sourcetypes.SourceType;

/**
 * Класс модели.
 * Хранит информацию о maven данных одной jar библиотеки.
 */
public class JarInfo {

    private Entry group = Entry.create();
    private Entry artifact = Entry.create();
    private Entry version = Entry.create();
    private String fileName = "";

    private JarInfo(Builder builder) {
        group = builder.group;
        artifact = builder.artifact;
        version = builder.version;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFormattedFileName(String format) {
        return String.format(format, fileName);
    }

    public String getFormattedResult(String format) {
        return String.format(format, group.getValue(), artifact.getValue(), version.getValue());
    }

    public String getFormattedSource(String format) {
        return String.format(format, version.getSource());
    }

    private static class Entry {

        protected String value = "";
        protected SourceType source = NoneSourceTypeDecorator.NONE;

        private Entry(String value, SourceType source) {
            this.value = value;
            this.source = source;
        }

        private Entry() {
            this("", NoneSourceTypeDecorator.NONE);
        }

        public static Entry create() {
            return new Entry();
        }

        private static Entry create(String value, SourceType source) {
            return new Entry(value, source);
        }

        public String getValue() {
            return value;
        }

        public SourceType getSource() {
            return source;
        }
    }

    public static class Builder {

        private Entry group = Entry.create();
        private Entry artifact = Entry.create();
        private Entry version = Entry.create();

        public Builder setGroup(String id, SourceType source) {
            group = Entry.create(id, source);
            return this;
        }

        public Builder setArtifact(String id, SourceType source) {
            artifact = Entry.create(id, source);
            return this;
        }

        public Builder setVersion(String id, SourceType source) {
            version = Entry.create(id, source);
            return this;
        }

        public JarInfo build() {
            return new JarInfo(this);
        }
    }
}
