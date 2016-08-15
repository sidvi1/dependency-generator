package ru.sidvi.depextractor.commands;

/**
 * Формирует сообщение об ошибке.
 */
public class FailCommand extends ResultHolder {

    public FailCommand(String message) {
        result = message;
    }
}
