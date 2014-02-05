package ru.sidvi.depextractor;

/**
 * Created by sidvi on 05.02.14.
 */
public class Info {
    private Version version = new Version();
    private Artifact artifact = new Artifact();

    public Info() {
    }

    public Info(Version version, Artifact artifact) {
        this.version = version;
        this.artifact = artifact;
    }

    public Version getVersion() {
        return version;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (artifact != null ? !artifact.equals(info.artifact) : info.artifact != null) return false;
        if (version != null ? !version.equals(info.version) : info.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (artifact != null ? artifact.hashCode() : 0);
        return result;
    }
}
