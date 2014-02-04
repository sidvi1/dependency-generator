package ru.sidvi.depextractor;

/**
 * Created by sidvi on 04.02.14.
 */
public class Info {

    private String name = "";
    private Version version = new Version();

    public Info(String name, Version version) {
        this.name = name;
        this.version = version;
    }

    public Info() {
    }

    public void setVersion(String ver) {
        version.setName(ver);
    }

    public void setVersionType(Version.Type versionType) {
        version.setType(versionType);
    }

    @Override
    public String toString() {
        return "Info{" +
                "name='" + name + '\'' +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (!name.equals(info.name)) return false;
        if (!version.equals(info.version)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + version.hashCode();
        return result;
    }

    public static class Version {

        private String name = "";
        private Type type = Type.NONE;

        public Version(Type type, String name) {
            this.name = name;
            this.type = type;
        }

        public Version() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Version{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Version version = (Version) o;

            if (!name.equals(version.name)) return false;
            if (type != version.type) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + type.hashCode();
            return result;
        }

        public enum Type {
            NONE("No field"),
            MANIFEST_IMPL_VERSION("MANIFEST Implementation version field"),
            MANIFEST_SPEC_VERSION("MANIFEST Specification version field"),
            MAVEN_POM_VERSION("MAVEN POM.XML version field");
            private final String value;

            Type(String val) {
                value = val;
            }

        }
    }
}
