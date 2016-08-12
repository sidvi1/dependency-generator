package ru.sidvi.depextractor.validators;

import java.io.File;

/**
 * Проверяет что первый элемент массива параметров - дирректория.
 */
public class DirectoryValidator extends BaseValidator {
    private String[] args;

    public DirectoryValidator(String[] args) {
        this.args = args;
    }

    @Override
    public boolean validate() {
        File dir = new File(args[0]);
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
