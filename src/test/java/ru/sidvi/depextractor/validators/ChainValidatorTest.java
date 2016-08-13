package ru.sidvi.depextractor.validators;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
@RunWith(value = MockitoJUnitRunner.class)
public class ChainValidatorTest {

    private ChainValidator chainValidator;

    @Mock
    private Validator validator;

    @Before
    public void setUp(){
        chainValidator = new ChainValidator();
    }

    @Test
    public void shouldFalse() throws Exception {
        when(validator.validate()).thenReturn(false);
        when(validator.getMessage()).thenReturn("Fail message");
        chainValidator.add(validator);

        boolean actual = chainValidator.validate();

        assertEquals(false, actual);
        assertEquals("Fail message", chainValidator.getMessage());
    }

     @Test
    public void shouldTrue() throws Exception {
        when(validator.validate()).thenReturn(true);
        chainValidator.add(validator);

        boolean actual = chainValidator.validate();

        assertEquals(true, actual);
    }
}