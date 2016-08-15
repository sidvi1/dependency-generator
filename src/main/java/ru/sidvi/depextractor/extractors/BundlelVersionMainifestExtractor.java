package ru.sidvi.depextractor.extractors;

import ru.sidvi.depextractor.extractors.manifest.BaseManifestExtractor;
import ru.sidvi.depextractor.extractors.sourcetypes.ManifestSourceTypeDecorator;

/**
 * Извлекает информацию из MANIFEST.MF
 */
public class BundlelVersionMainifestExtractor extends BaseManifestExtractor {

    private static final String FIELD_NAME = "Bundle-Version";

    @Override
    protected void parseLine(String[] split) {
        extractFieldValue(split, FIELD_NAME, ManifestSourceTypeDecorator.MF_BUNDLE_VERSION);
    }
}
