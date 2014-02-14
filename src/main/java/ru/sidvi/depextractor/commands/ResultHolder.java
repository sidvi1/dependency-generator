package ru.sidvi.depextractor.commands;

/**
 * Created by Vitaly A. Sidorov on 14.02.14.
 */
public abstract class ResultHolder implements Command {
    protected String result;
    protected String message = "";

    public String getResult() {
        return result;
    }


    @Override
    public void setMessage(String message) {
        this.result = message;
    }
}
