package ru.sidvi.depextractor;

/**
 * Created by sidvi on 04.02.14.
 */
public class Artifact {
    private String value = "";
    private Source source = NoneSource.NONE;

    public Artifact() {
    }

    public Artifact(String value, Source source) {
        this.value = value;
        this.source = source;
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
