package ru.sidvi.depextractor.validators;

public abstract class BaseValidator implements Validator {

    protected String message;

    @Override
    public String getMessage() {
        return message;
    }
}
