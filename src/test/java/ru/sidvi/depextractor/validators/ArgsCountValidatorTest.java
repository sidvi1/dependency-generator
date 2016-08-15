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
        validator = new ArgsCountValidator(new String[]{"1"});

        boolean actual = validator.validate();

        assertEquals(true, actual);
    }

    @Test
    public void shouldFailNoArgs() {
        validator = new ArgsCountValidator(null);

        boolean actual = validator.validate();

        assertEquals(false, actual);
    }
   @Test
    public void shouldFailZeroArgs() {
        validator = new ArgsCountValidator(new String[]{});

        boolean actual = validator.validate();

        assertEquals(false, actual);
    }

    @Test
    public void shouldFail() {
        validator = new ArgsCountValidator(new String[]{"1", "2"});

        boolean actual = validator.validate();

        assertEquals(false, actual);
    }
}
