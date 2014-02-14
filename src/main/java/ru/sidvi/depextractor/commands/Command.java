package ru.sidvi.depextractor.commands;

/**
 * Created by sidvi on 08.02.14.
 */
public interface Command {

    void setMessage(String message);

    void execute();

    String getResult();
}
