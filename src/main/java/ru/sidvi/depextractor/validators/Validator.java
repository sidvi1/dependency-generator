package ru.sidvi.depextractor.validators;

/**
 * ��������� ��������� ���������� ������.
 */
public interface Validator {
    /**
     * �������� ��������.
     * @return - true ���� �������� ��������.
     */
    boolean validate();

    /**
     * �������� ��������� �� ������ � ������ ���� �������� �� �������: validate() ������ false.
     * @return
     */
    String getMessage();
}
