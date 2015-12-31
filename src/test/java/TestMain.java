
import org.apache.commons.io.IOUtils;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import ru.sidvi.depextractor.commands.Command;
import ru.sidvi.depextractor.commands.CommandFactory;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Vitaly A. Sidorov on 15.02.14.
 */
public class TestMain {
    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    @Test
//    @PerfTest(duration = 20000, threads = 1)
    public void shouldSerializeRandomUser() throws JAXBException, IOException {
        Command command = CommandFactory.create(new String[]{"v:\\Java\\_projects\\serviio_backup\\lib"});
        command.execute();
        assertThat(command.getResult(), is(string("out.txt")));
    }
    private String string(String s) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(s));
    }
}
