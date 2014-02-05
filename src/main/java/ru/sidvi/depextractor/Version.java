package ru.sidvi.depextractor;

/**
* Created by sidvi on 04.02.14.
*/
public class Version {

    private String value = "";
    private Type type = Type.NONE;

    public Version(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Version() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isFullFilled() {
        return !value.isEmpty();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Version version = (Version) o;

        return value.equals(version.value) && type == version.type;

    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Version{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
