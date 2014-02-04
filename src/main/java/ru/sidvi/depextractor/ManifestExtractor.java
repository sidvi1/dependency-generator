package ru.sidvi.depextractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sidvi on 04.02.14.
 */
public class ManifestExtractor implements InfoExtractor {

    public static final String IMPLEMENTATION_VERSION = "Implementation-Version";
    public static final String SPECIFICATION_VERSION = "Specification-Version";

    private InputStream is;

    public ManifestExtractor(InputStream is) {
        this.is = is;
    }

    @Override
    public Info extract() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        Info info = new Info();
        while ((line = readLine(reader)) != null) {
            String[] split = line.split(":");
            if (tryToGetSpecificationVersion(info, split)) break;
            if (tryToGetImplementationVersion(info, split)) break;
        }
        return info;
    }

    private String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException ignored) {
        }
        return null;
    }


    private boolean tryToGetImplementationVersion(Info info, String[] split) {
        String field = IMPLEMENTATION_VERSION;
        return extractFieldValue(info, split, field, Info.Version.Type.MANIFEST_IMPL_VERSION);
    }

    private boolean tryToGetSpecificationVersion(Info info, String[] split) {
        return extractFieldValue(info, split, SPECIFICATION_VERSION, Info.Version.Type.MANIFEST_SPEC_VERSION);
    }

    private boolean extractFieldValue(Info info, String[] split, String field, Info.Version.Type versionType) {
        if (split[0].trim().equals(field)) {
            info.setVersion(split[1].trim());
            info.setVersionType(versionType);
            return true;
        }
        return false;
    }
}
