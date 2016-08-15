package ru.sidvi.depextractor.formatters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ru.sidvi.depextractor.extractors.sourcetypes.ManifestSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import static org.junit.Assert.assertEquals;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
@RunWith(value = MockitoJUnitRunner.class)
public class TableFormatterTest {

    private TableFormatter formatter;

    @Before
    public void setUp(){
        formatter = new TableFormatter();
    }

    @Test
    public void shouldFormat() throws Exception {
        JarInfo info = new JarInfo.Builder()
                .setArtifact("art", ManifestSourceTypeDecorator.MF_SPECIFICATION_VERSION)
                .setVersion("ver", ManifestSourceTypeDecorator.MF_SPECIFICATION_VERSION)
                .setGroup("group", ManifestSourceTypeDecorator.MF_SPECIFICATION_VERSION)
                .build();
        info.setFileName("file.jar");

        String actual = formatter.format(info);

        assertEquals("file.jar                       MF_SPECIFICATION_VERSION                           group:art:ver\r\n", actual);
    }
}