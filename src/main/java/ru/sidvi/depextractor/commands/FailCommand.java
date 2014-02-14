package ru.sidvi.depextractor.commands;

/**
 * Created by sidvi on 08.02.14.
 */
public class FailCommand extends ResultHolder  {

    @Override
    public void execute() {
        result = message;
    }

}
