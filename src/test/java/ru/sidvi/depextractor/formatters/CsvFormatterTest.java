package ru.sidvi.depextractor.formatters;

import org.junit.Before;
import org.junit.Test;
import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import static org.junit.Assert.assertEquals;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
public class CsvFormatterTest {

    public CsvFormatter tested;

    public JarInfo info;

    @Before
    public void setUp() {
        tested = new CsvFormatter(";");

        info = new JarInfo.Builder(PomSourceTypeDecorator.POM_XML)
                .setGroup("group")
                .setArtifact("artifact")
                .setVersion("version")
                .build();
        info.setFileName("file.jar");
    }

    @Test
    public void testFormat() throws Exception {
        String actual = tested.format(info);
        assertEquals("file.jar;POM_XML;group;artifact;version\r\n", actual);
    }
}