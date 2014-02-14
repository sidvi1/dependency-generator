package ru.sidvi.depextractor.commands;

/**
 * Created by Vitaly A. Sidorov on 08.02.14.
 */
public interface Command {

    void setMessage(String message);

    void execute();

    String getResult();
}
