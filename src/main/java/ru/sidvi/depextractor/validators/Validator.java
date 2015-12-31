package ru.sidvi.depextractor.validators;

/**
 * Проверяет аргументы коммандной строки.
 */
public interface Validator {
    /**
     * Провести проверку.
     * @return - true если проверка успешная.
     */
    boolean validate();

    /**
     * Получить сообщение об ошибке в случае если проверка не удалась: validate() вернул false.
     * @return
     */
    String getMessage();
}
