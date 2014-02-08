package ru.sidvi.depextractor;

/**
 * Created by sidvi on 08.02.14.
 */
public interface JarInfo {

    void setVersion(Entry version);

    boolean isFullFilled();

    String getFileName();

    void setFileName(String fileName);

    String getFormatedName(String s);

    String getFormattedResult(String format);

    String getFormatedSource(String s);


    interface Entry {

        boolean isFullFilled();

        String getValue();

        Source getSource();
    }

}
