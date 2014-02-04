package ru.sidvi.depextractor;

/**
 * Created by sidvi on 04.02.14.
 */
public class Artifact {
    private String group = "";
    private String artifact = "";

    public Artifact(String group, String artifact) {
        this.group = group;
        this.artifact = artifact;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artifact artifact1 = (Artifact) o;

        if (artifact != null ? !artifact.equals(artifact1.artifact) : artifact1.artifact != null) return false;
        if (group != null ? !group.equals(artifact1.group) : artifact1.group != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = group != null ? group.hashCode() : 0;
        result = 31 * result + (artifact != null ? artifact.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "group='" + group + '\'' +
                ", artifact='" + artifact + '\'' +
                '}';
    }
}
