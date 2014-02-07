package ru.sidvi.depextractor;

/**
 * Created by sidvi on 05.02.14.
 */
public class Info {

    private Group group = new Group();
    private Artifact artifact = new Artifact();
    private Version version = new Version();
    private String fileName = "";

    public Info() {
    }

    public Info(Group group, Artifact artifact, Version version) {
        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }

    public boolean isFullFilled() {
        return version.isFullFilled() && artifact.isFullFilled() && group.isFullFilled();
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFormatedName(String s) {
        return String.format(s, fileName);
    }

    public String getFormattedResult(String format) {
        return String.format(format, group.getValue(), artifact.getValue(), version.getValue());
    }

    public String getFormatedSource(String s) {
        return String.format(s, version.getSource());
    }
}
