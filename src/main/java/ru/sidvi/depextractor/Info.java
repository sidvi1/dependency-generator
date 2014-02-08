package ru.sidvi.depextractor;

/**
 * Created by sidvi on 05.02.14.
 */
public class Info implements JarInfo {

    private Entry group = SimpleEntry.create();
    private Entry artifact = SimpleEntry.create();
    private Entry version = SimpleEntry.create();
    private String fileName = "";

    private Info() {
    }

    private Info(SimpleEntry group, SimpleEntry artifact, SimpleEntry version) {
        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }

    private Info(Builder builder) {
        group = builder.group;
        artifact = builder.artifact;
        version = builder.version;
    }

    public static JarInfo instance() {
        return new Info();
    }

    @Override
    public boolean isFullFilled() {
        return version.isFullFilled() && artifact.isFullFilled() && group.isFullFilled();
    }

    @Override
    public void setVersion(SimpleEntry version) {
        this.version = version;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getFormatedName(String s) {
        return String.format(s, fileName);
    }

    @Override
    public String getFormattedResult(String format) {
        return String.format(format, group.getValue(), artifact.getValue(), version.getValue());
    }

    @Override
    public String getFormatedSource(String s) {
        return String.format(s, version.getSource());
    }

    public static class SimpleEntry implements Entry {

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

        @Override
        public boolean isFullFilled() {
            return !value.isEmpty();
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public Source getSource() {
            return source;
        }

    }

    public static class Builder {

        private Entry group;
        private Entry artifact;
        private Entry version;

        public Builder() {
            group = SimpleEntry.create();
            artifact = SimpleEntry.create();
            version = SimpleEntry.create();
        }

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

        public Info build() {
            return new Info(this);
        }
    }
}
