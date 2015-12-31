package ru.sidvi.depextractor.commands;

/**
 *  Формирует сообщение об ошибке.
 */
public class FailCommand extends ResultHolder {

    FailCommand(String message) {
        result = message;
    }
}
