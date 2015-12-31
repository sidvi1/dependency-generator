package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.JarInfo;

/**
 * Created by Vitaly A. Sidorov on 05.02.14.
 */
class Info implements JarInfo {

    private Entry group = SimpleEntry.create();
    private Entry artifact = SimpleEntry.create();
    private Entry version = SimpleEntry.create();
    private String fileName = "";

    private Info(Builder builder) {
        group = builder.group;
        artifact = builder.artifact;
        version = builder.version;
    }


    public void setVersion(Entry version) {
        this.version = version;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFormattedName(String s) {
        return String.format(s, fileName);
    }

    public String getFormattedResult(String format) {
        return String.format(format, group.getValue(), artifact.getValue(), version.getValue());
    }

    public String getFormattedSource(String s) {
        return String.format(s, version.getSource());
    }

    private static class SimpleEntry implements Entry {

        protected String value = "";
        protected Source source = NoneSource.NONE;

        private SimpleEntry(String value, Source source) {
            this.value = value;
            this.source = source;
        }

        private SimpleEntry() {
            this("", NoneSource.NONE);
        }

        public static Entry create() {
            return new SimpleEntry();
        }

        private static Entry create(String value, Source source) {
            return new SimpleEntry(value, source);
        }


        public String getValue() {
            return value;
        }

        public Source getSource() {
            return source;
        }
    }

    static class Builder {

        private Entry group = SimpleEntry.create();
        private Entry artifact = SimpleEntry.create();
        private Entry version = SimpleEntry.create();

        public Builder setGroup(String id, Source source) {
            group = SimpleEntry.create(id, source);
            return this;
        }

        public Builder setArtifact(String id, Source source) {
            artifact = SimpleEntry.create(id, source);
            return this;
        }

        public Builder setVersion(String id, Source source) {
            version = SimpleEntry.create(id, source);
            return this;
        }

        public JarInfo build() {
            return new Info(this);
        }
    }
}
