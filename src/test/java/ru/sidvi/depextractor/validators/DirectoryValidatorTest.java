package ru.sidvi.depextractor.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
@RunWith(value = MockitoJUnitRunner.class)
public class DirectoryValidatorTest {

    @InjectMocks
    private DirectoryValidator validator;

    @Mock
    private File dir;

    @Test
    public void shoudValidate() {
        when(dir.getAbsolutePath()).thenReturn("absolute_path");
        when(dir.exists()).thenReturn(true);
        when(dir.isDirectory()).thenReturn(true);

        boolean actual = validator.validate();

        assertEquals(true, actual);
    }

    @Test
    public void shouldFailNotDirectory() {
        when(dir.getAbsolutePath()).thenReturn("absolute_path");
        when(dir.exists()).thenReturn(true);
        when(dir.isDirectory()).thenReturn(false);

        boolean actual = validator.validate();

        assertEquals(false, actual);
    }

    @Test
    public void shouldFailNotExists() {
        when(dir.getAbsolutePath()).thenReturn("absolute_path");
        when(dir.exists()).thenReturn(false);
        when(dir.isDirectory()).thenReturn(true);

        boolean actual = validator.validate();

        assertEquals(false, actual);
    }
}