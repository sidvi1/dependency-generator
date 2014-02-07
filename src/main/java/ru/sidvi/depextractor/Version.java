package ru.sidvi.depextractor;

/**
 * Created by sidvi on 04.02.14.
 */
public class Version {

    private String value = "";
    private Source source = NoneSource.NONE;

    public Version(String value, Source source) {
        this.value = value;
        this.source = source;
    }

    public Version() {
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
