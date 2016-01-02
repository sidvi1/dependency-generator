package ru.sidvi.depextractor;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import ru.sidvi.depextractor.commands.Command;
import ru.sidvi.depextractor.commands.CommandFactory;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestMain {
    @Test
    public void integrationTest1() throws IOException {
        String jarsPath = getClass().getClassLoader().getResource("jars").getPath();
        Command command = CommandFactory.create(new String[]{jarsPath});
        command.execute();
        String actual = command.getResult();

        assertThat(actual, is(string("fixtures/program_out.txt")));
    }

    private String string(String s) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(s));
    }
}
