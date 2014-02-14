package ru.sidvi.depextractor.commands;

/**
 * Created by sidvi on 08.02.14.
 */
public class HelpCommand extends ResultHolder implements Command {

    @Override
    public void execute() {
        result = "Usage: dep.jar <file_path>";
    }

}
