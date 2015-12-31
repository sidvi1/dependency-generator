package ru.sidvi.depextractor.validators;

/**
 * Created by sidvi on 31.12.2015.
 */
public abstract class BaseValidator implements Validator {

    protected String message;

    public String getMessage() {
        return message;
    }
}
