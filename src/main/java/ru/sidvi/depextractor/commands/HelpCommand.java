package ru.sidvi.depextractor.commands;

/**
 * ��������� ������ � �������.
 */
public class HelpCommand extends ResultHolder {

    public void execute() {
        result = "Usage: dep.jar <file_path>";
    }

}
