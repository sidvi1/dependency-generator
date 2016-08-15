package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.manifest.BaseManifestExtractor;
import ru.sidvi.depextractor.extractors.sourcetypes.ManifestSourceTypeDecorator;

/**
 * Извлекает информацию из MANIFEST.MF
 */
public class ImpllVersionMainifestExtractor extends BaseManifestExtractor {

    private static final String IMPLEMENTATION_VERSION = "Implementation-Version";

    @Override
    protected void parseLine(String[] split) {
        extractFieldValue(split, IMPLEMENTATION_VERSION, ManifestSourceTypeDecorator.MF_IMPLEMENTATION_VERSION);
    }
}
