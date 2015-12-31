import org.apache.commons.io.IOUtils;
import org.junit.Test;
import ru.sidvi.depextractor.extractors.PomParser;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Vitaly A. Sidorov on 15.02.14.
 */
public class TestPomParser {

    @Test
    public void shouldWork(){
        InputStream is = getClass().getClassLoader().getResourceAsStream("sample2_pom.xml");

        PomParser parser = new PomParser();
        parser.parse(is);

        assertThat(parser.getParentGroupId(), is("org.slf4j"));
    }

    private String string(String s) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(s));
    }
}