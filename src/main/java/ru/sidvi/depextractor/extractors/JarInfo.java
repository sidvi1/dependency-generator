package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.sources.Source;

/**
 * Created byVitaly A. Sidorovon 08.02.14.
 */
public interface JarInfo {

    void setVersion(Entry version);

    void setFileName(String fileName);

    String getFormattedName(String s);

    String getFormattedResult(String format);

    String getFormattedSource(String s);


    interface Entry {

        String getValue();

        Source getSource();
    }

}
