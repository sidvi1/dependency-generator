package ru.sidvi.depextractor.validators;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
public class ArgsCountValidatorTest {

    private ArgsCountValidator validator;

    @Test
    public void shoudValidate() {
        validator = new ArgsCountValidator();

        boolean actual = validator.validate(new String[]{""});

        assertEquals(true, actual);
    }

    @Test
    public void shouldFailNoArgs() {
        validator = new ArgsCountValidator();

        boolean actual = validator.validate(null);

        assertEquals(false, actual);
    }

    @Test
    public void shouldFailZeroArgs() {
        validator = new ArgsCountValidator();

        boolean actual = validator.validate(new String[]{});

        assertEquals(false, actual);
    }

    @Test
    public void shouldFail() {
        validator = new ArgsCountValidator();

        boolean actual = validator.validate(new String[]{"1", "2"});

        assertEquals(false, actual);
    }
}
