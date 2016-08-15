package ru.sidvi.depextractor.validators;

/**
 * Проверяет число параметров.
 */
public class ArgsCountValidator extends BaseValidator {

    @Override
    public boolean validate(String[] args) {
        if (args == null || args.length == 0) {
            message = "Too low arguments.";
            return false;
        }
        if (args.length > 2) {
            message = "Too much arguments.";
            return false;
        }
        return true;
    }
}
