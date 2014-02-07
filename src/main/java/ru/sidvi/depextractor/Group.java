package ru.sidvi.depextractor;

/**
 * Created by sidvi on 07.02.14.
 */
public class Group {
    private String value = "";
    private Source source = NoneSource.NONE;

    public Group(String value, Source source) {
        this.value = value;
        this.source = source;
    }

    public Group() {
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
