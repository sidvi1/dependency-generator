package ru.sidvi.depextractor;

/**
 * Created by sidvi on 05.02.14.
 */
public class Info extends BaseInfo{

    private BaseElement group = BaseElement.create();
    private BaseElement artifact = BaseElement.create();
    private BaseElement version = BaseElement.create();
    private String fileName = "";

    public Info() {
    }

    public Info(BaseElement group, BaseElement artifact, BaseElement version) {
        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }

    public static BaseElement create(String value, Source source) {
        return new BaseElement(value, source);
    }

    @Override
    public boolean isFullFilled() {
        return version.isFullFilled() && artifact.isFullFilled() && group.isFullFilled();
    }

    @Override
    public void setVersion(BaseElement version) {
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

    /**
     * Created by sidvi on 08.02.14.
     */
    public static class BaseElement {

        protected String value = "";
        protected Source source = NoneSource.NONE;

        private BaseElement(String value, Source source) {
            this.value = value;
            this.source = source;
        }

        private BaseElement() {
            this("", NoneSource.NONE);
        }

        public static BaseElement create() {
            return new BaseElement();
        }

        public boolean isFullFilled() {
            return !value.isEmpty();
        }

        public String getValue() {
            return value;
        }

        public Source getSource() {
            return source;
        }


    }
}
