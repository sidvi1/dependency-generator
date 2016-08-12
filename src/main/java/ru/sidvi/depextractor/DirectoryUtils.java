package ru.sidvi.depextractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryUtils {
    /**
     * Рекурсивно обходит все дирректории и возвращает файлы с заданным расширением.
     *
     * @param dir
     * @param extension
     * @return
     */
    static public List<File> list(File dir, final String extension) {
        List<File> result = new ArrayList<File>();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                result.addAll(list(file, extension));
            } else {
                if (file.getName().endsWith(extension)) {
                    result.add(file);
                }
            }
        }
        return result;
    }
}
