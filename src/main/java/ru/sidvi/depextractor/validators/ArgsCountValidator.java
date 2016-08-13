package ru.sidvi.depextractor.validators;

/**
 * Проверяет число параметров.
 */
public class ArgsCountValidator extends BaseValidator {

    private int count;

    public ArgsCountValidator(int count) {
        this.count = count;
    }

    @Override
    public boolean validate() {
        if (count  == 0) {
            message = "Too low arguments.";
            return false;
        }
        if (count > 1) {
            message = "Too much arguments.";
            return false;
        }
        return true;
    }
}
