package ru.sidvi.depextractor.validators;

/**
 * Проверяет число параметров.
 */
public class ArgsCountValidator extends BaseValidator {

    private String[] args;

    public ArgsCountValidator(String[] count) {
        this.args = count;
    }

    @Override
    public boolean validate() {
        if (args == null || args.length == 0) {
            message = "Too low arguments.";
            return false;
        }
        if (args.length > 1) {
            message = "Too much arguments.";
            return false;
        }
        return true;
    }
}
