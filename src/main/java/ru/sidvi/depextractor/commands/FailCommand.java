package ru.sidvi.depextractor.commands;

/**
 * Created by sidvi on 08.02.14.
 */
public class FailCommand extends ResultHolder implements Command {

    private String message = "";

    public FailCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        result = message;
    }

}
