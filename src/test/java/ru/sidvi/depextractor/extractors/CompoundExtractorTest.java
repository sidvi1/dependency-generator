package ru.sidvi.depextractor.extractors;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.sidvi.depextractor.extractors.sourcetypes.PomSourceTypeDecorator;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
@RunWith(value = MockitoJUnitRunner.class)
public class CompoundExtractorTest {

    public CompoundExtractor extractor;

    public InputStream is;

    @Mock
    public Extractor firstExtractor;

    @Mock
    public Extractor secondExtractor;

    @Before
    public void setUp() {
        extractor = new CompoundExtractor();
        is = IOUtils.toInputStream("here_must_be_data_from_file_to_extact");
    }

    @Test
    public void shouldExtract() {
        extractor.add(firstExtractor);
        when(firstExtractor.extract(any(InputStream.class))).thenReturn(buildList("group", "artifact", "version"));

        List<JarInfo> actual = extractor.extract(is);

        verify(firstExtractor, times(1)).extract(any(InputStream.class));
        assertEquals(1, actual.size());
        assertThat(actual, IsCollectionContaining.hasItem(buildJarInfo("group", "artifact", "version")));
    }

    @Test
    public void shouldExtractMultiple() {
        extractor.add(firstExtractor)
                .add(secondExtractor);
        when(firstExtractor.extract(any(InputStream.class))).thenReturn(buildList("group", "artifact", "version"));
        when(secondExtractor.extract(any(InputStream.class))).thenReturn(buildList("group2", "artifact2", "version2"));

        List<JarInfo> actual = extractor.extract(is);

        verify(firstExtractor, times(1)).extract(any(InputStream.class));
        verify(secondExtractor, times(1)).extract(any(InputStream.class));
        assertEquals(2, actual.size());
        assertThat(actual, IsCollectionContaining.hasItems(buildJarInfo("group", "artifact", "version")
                , buildJarInfo("group2", "artifact2", "version2")));
    }

    private List<JarInfo> buildList(String group, String artifact, String version) {
        List<JarInfo> list = new ArrayList<>();
        list.add(buildJarInfo(group, artifact, version));
        return list;
    }

    private JarInfo buildJarInfo(String group, String artifact, String version) {
        return new JarInfo.Builder(PomSourceTypeDecorator.POM_XML)
                .setGroup(group)
                .setArtifact(artifact)
                .setVersion(version)
                .build();
    }

}