package ru.sidvi.depextractor;

/**
 * Created by sidvi on 05.02.14.
 */
public class Info extends BaseInfo{

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

    @Override
    public boolean isFullFilled() {
        return version.isFullFilled() && artifact.isFullFilled() && group.isFullFilled();
    }

    @Override
    public void setVersion(Version version) {
        this.version = version;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getFormatedName(String s) {
        return String.format(s, fileName);
    }

    @Override
    public String getFormattedResult(String format) {
        return String.format(format, group.getValue(), artifact.getValue(), version.getValue());
    }

    @Override
    public String getFormatedSource(String s) {
        return String.format(s, version.getSource());
    }
}
