package ru.sidvi.depextractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sidvi on 04.02.14.
 */
public class ManifestExtractor implements VersionExtractor {

    public static final String IMPLEMENTATION_VERSION = "Implementation-Version";
    public static final String SPECIFICATION_VERSION = "Specification-Version";

    private InputStream is;

    public ManifestExtractor(InputStream is) {
        this.is = is;
    }

    @Override
    public Version extract() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        Version ver = new Version();
        while ((line = readLine(reader)) != null) {
            String[] split = line.split(":");
            if (tryToGetSpecificationVersion(ver, split)) break;
            if (tryToGetImplementationVersion(ver, split)) break;
        }
        return ver;
    }

    private String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException ignored) {
        }
        return null;
    }


    private boolean tryToGetImplementationVersion(Version info, String[] split) {
        String field = IMPLEMENTATION_VERSION;
        return extractFieldValue(info, split, field, Version.Type.MANIFEST_IMPL_VERSION);
    }

    private boolean tryToGetSpecificationVersion(Version info, String[] split) {
        return extractFieldValue(info, split, SPECIFICATION_VERSION, Version.Type.MANIFEST_SPEC_VERSION);
    }

    private boolean extractFieldValue(Version info, String[] split, String field, Version.Type versionType) {
        if (split[0].trim().equals(field)) {
            info.setName(split[1].trim());
            info.setType(versionType);
            return true;
        }
        return false;
    }
}
