package ru.sidvi.depextractor.commands;

/**
 * Формирует строку с помощью.
 */
public class HelpCommand extends ResultHolder {

    @Override
    public void execute() {
        result = "Usage: dep.jar <file_path>";
    }

}
