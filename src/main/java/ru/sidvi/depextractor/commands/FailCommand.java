package ru.sidvi.depextractor.commands;

/**
 *  ��������� ��������� �� ������.
 */
public class FailCommand extends ResultHolder {

    FailCommand(String message) {
        result = message;
    }
}
