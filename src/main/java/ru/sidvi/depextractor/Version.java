package ru.sidvi.depextractor;

/**
* Created by sidvi on 04.02.14.
*/
public class Version {

    private String name = "";
    private Type type = Type.NONE;

    public Version(Type type, String name) {
        this.type = type;
        this.name = name;
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

        return name.equals(version.name) && type == version.type;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Version{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
