package ru.sidvi.depextractor;

/**
 * Created by sidvi on 07.02.14.
 */
public abstract class BaseInfo {
    abstract boolean isFullFilled();

    abstract void setVersion(Version version);

    abstract String getFileName();

    abstract void setFileName(String fileName);

    abstract String getFormatedName(String s);

    abstract String getFormattedResult(String format);

    abstract String getFormatedSource(String s);

    public static BaseInfo instance() {
        return new Info();
    }
}
