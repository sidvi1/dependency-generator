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

    @Override
    public String toString() {
        return getFormattedResult("%s:%s:%s");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JarInfo jarInfo = (JarInfo) o;

        if (group != null ? !group.equals(jarInfo.group) : jarInfo.group != null) return false;
        if (artifact != null ? !artifact.equals(jarInfo.artifact) : jarInfo.artifact != null) return false;
        if (version != null ? !version.equals(jarInfo.version) : jarInfo.version != null) return false;
        return !(fileName != null ? !fileName.equals(jarInfo.fileName) : jarInfo.fileName != null);

    }

    @Override
    public int hashCode() {
        int result = group != null ? group.hashCode() : 0;
        result = 31 * result + (artifact != null ? artifact.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry entry = (Entry) o;

            if (value != null ? !value.equals(entry.value) : entry.value != null) return false;
            return !(source != null ? !source.equals(entry.source) : entry.source != null);

        }

        @Override
        public int hashCode() {
            int result = value != null ? value.hashCode() : 0;
            result = 31 * result + (source != null ? source.hashCode() : 0);
            return result;
        }
    }

    public static class Builder {

        private Entry group = Entry.create();
        private Entry artifact = Entry.create();
        private Entry version = Entry.create();
        private SourceType source;

        public Builder(SourceType source) {
            this.source = source;
        }

        public Builder setGroup(String id) {
            group = Entry.create(id, source);
            return this;
        }

        public Builder setArtifact(String id) {
            artifact = Entry.create(id, source);
            return this;
        }

        public Builder setVersion(String id) {
            version = Entry.create(id, source);
            return this;
        }

        public JarInfo build() {
            return new JarInfo(this);
        }
    }
}
