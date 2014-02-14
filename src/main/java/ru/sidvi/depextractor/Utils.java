package ru.sidvi.depextractor;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Vitaly A. Sidorov on 14.02.14.
 */
public class Utils {
    static public File[] list(File dir, final String extension) {
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {

                return s.toLowerCase().endsWith(extension);
            }
        });
    }
}
