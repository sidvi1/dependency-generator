package ru.sidvi.depextractor.validators;

import java.util.ArrayList;
import java.util.List;

/**
 * Вызывает все валидаторы по порядку.
 * В случае если хотя бы один не проходит проверку, возвращает false.
 */
public class ChainValidator extends BaseValidator {
    private List<Validator> validators = new ArrayList<Validator>();

    @Override
    public boolean validate() {
        for (Validator v : validators) {
            if (!v.validate()) {
                message = v.getMessage();
                return false;
            }
        }
        return true;
    }

    public ChainValidator add(Validator validator) {
        validators.add(validator);
        return this;
    }
}