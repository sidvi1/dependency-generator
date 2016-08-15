package ru.sidvi.depextractor.validators;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
public class CsvArgumentValidator extends BaseValidator{

    public static final String ARGUMENT = "-csv";

    @Override
    public boolean validate(String[] args) {
        for (String arg : args) {
            if(ARGUMENT.equals(arg)){
                return  true;
            }
        }
        return false;
    }
}
