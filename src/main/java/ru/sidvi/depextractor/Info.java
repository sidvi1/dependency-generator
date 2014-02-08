package ru.sidvi.depextractor;

/**
 * Created by sidvi on 05.02.14.
 */
public class Info implements JarInfo {

    private SimpleEntry group = SimpleEntry.create();
    private SimpleEntry artifact = SimpleEntry.create();
    private SimpleEntry version = SimpleEntry.create();
    private String fileName = "";

    public Info() {
    }

    public Info(SimpleEntry group, SimpleEntry artifact, SimpleEntry version) {
        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }

    public static SimpleEntry create(String value, Source source) {
        return SimpleEntry.createSimpleEntry(value, source);
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

        public static SimpleEntry create() {
            return createSimpleEntry();
        }

        private static SimpleEntry createSimpleEntry() {
            return new SimpleEntry();
        }

        private static SimpleEntry createSimpleEntry(String value, Source source) {
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
}
