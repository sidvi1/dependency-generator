package ru.sidvi.depextractor;

/**
 * Created by sidvi on 08.02.14.
 */
public abstract class BaseElement {

    protected String value = "";
    protected Source source = NoneSource.NONE;

    public BaseElement(String value, Source source) {
        this.value = value;
        this.source = source;
    }

    public BaseElement() {
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
