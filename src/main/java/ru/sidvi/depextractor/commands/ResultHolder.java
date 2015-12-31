package ru.sidvi.depextractor.commands;

/**
 * Created by Vitaly A. Sidorov on 14.02.14.
 */
public abstract class ResultHolder implements Command {
    protected String result = "";

    public String getResult() {
        return result;
    }

    public void execute() {
    }
}
