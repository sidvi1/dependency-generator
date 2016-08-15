package ru.sidvi.depextractor.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
@RunWith(value = MockitoJUnitRunner.class)
public class DirectoryValidatorTest {

    @InjectMocks
    private DirectoryValidator validator;

    private String[] argsStub;


    @Test
    public void shoudValidate() {
        argsStub = new String[]{this.getClass().getClassLoader().getResource("files").getPath()};

        boolean actual = validator.validate(argsStub);

        assertEquals(true, actual);
    }

    @Test
    public void shouldFailNotDirectory() {
        argsStub = new String[]{"some_directory"};

        boolean actual = validator.validate(argsStub);

        assertEquals(false, actual);
    }

    @Test
    public void shouldFailNotExists() {
        argsStub = new String[]{"some_directory"};

        boolean actual = validator.validate(argsStub);

        assertEquals(false, actual);
    }
}