package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.manifest.BaseManifestExtractor;
import ru.sidvi.depextractor.extractors.sourcetypes.ManifestSourceTypeDecorator;

/**
 * @author Vitaly Sidorov mail@vitaly-sidorov.com
 */
public class SpecVersionManifestExtractor extends BaseManifestExtractor {

    private static final String SPECIFICATION_VERSION = "Specification-Version";

    @Override
    protected void parseLine(String[] split) {
        extractFieldValue(split, SPECIFICATION_VERSION, ManifestSourceTypeDecorator.MF_SPECIFICATION_VERSION);
    }
}
