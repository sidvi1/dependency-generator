package ru.sidvi.depextractor.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Вызывает все валидаторы в порядке очередности.
 */
public class ChainValidator extends BaseValidator {
    private List<Validator> validators = new ArrayList<Validator>();

    public ChainValidator(Validator[] chain) {
        validators.addAll(Arrays.asList(chain));
    }

    public boolean validate() {
        for (Validator v : validators) {
            if (!v.validate()) {
                message = v.getMessage();
                return false;
            }
        }
        return true;
    }
}
