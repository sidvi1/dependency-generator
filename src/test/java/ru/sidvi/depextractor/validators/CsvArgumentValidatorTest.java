package ru.sidvi.depextractor.validators;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
public class CsvArgumentValidatorTest {

    public CsvArgumentValidator tested;

    @Before
    public void setUp() {
        tested = new CsvArgumentValidator();
    }

    @Test
    public void shouldValidate() throws Exception {
        String[] args = new String[]{"param1", "param2", "param3", CsvArgumentValidator.ARGUMENT, "param5"};
        boolean actual = tested.validate(args);

        assertEquals(true, actual);
    }

    @Test
    public void shouldFail() throws Exception {
        String[] args = new String[]{"param1", "param2", "param3", "param4", "param5"};
        boolean actual = tested.validate(args);

        assertEquals(false, actual);
    }
}