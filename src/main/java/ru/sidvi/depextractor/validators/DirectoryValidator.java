package ru.sidvi.depextractor.validators;

import java.io.File;

/**
 * Проверяет что первый элемент массива параметров - дирректория.
 */
public class DirectoryValidator extends BaseValidator {

    private File dir;

    public DirectoryValidator(File dir) {
        this.dir = dir;
    }

    @Override
    public boolean validate() {
        String path = dir.getAbsolutePath();
        if (!dir.exists()) {
            message = String.format("Sorry, '%s' not exists. Try again.", path);
            return false;
        }
        if (!dir.isDirectory()) {
            message = String.format("Sorry, '%s' is not a directory. Try again.", path);
            return false;
        }
        return true;
    }
}
