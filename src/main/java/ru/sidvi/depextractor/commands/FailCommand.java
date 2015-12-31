package ru.sidvi.depextractor.commands;

/**
 * Created by Vitaly A. Sidorov on 08.02.14.
 */
public class FailCommand extends ResultHolder {

    public FailCommand(String message) {
        result = message;
    }

}
