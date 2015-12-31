package ru.sidvi.depextractor.commands;

/**
 * Created by Vitaly A. Sidorov on 08.02.14.
 */
public class HelpCommand extends ResultHolder {

    public void execute() {
        result = "Usage: dep.jar <file_path>";
    }

}
