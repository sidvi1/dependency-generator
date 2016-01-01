package ru.sidvi.depextractor.extractors.pom;

/**
 * Created by sidvi on 01.01.2016.
 */
class SimpleTag implements Tag {
    private boolean started;
    private String name;
    private String value = "";
    private int levelValue;

    public SimpleTag(String name, int levelValue) {
        this.name = name;
        this.levelValue = levelValue;
    }

    public boolean isStarted() {
        return started;
    }

    private void setStarted(boolean started) {
        this.started = started;
    }

    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

    public void checkForStart(String name) {
        if (this.name.equals(name)) {
            setStarted(true);
        }
    }

    public void checkForEnd(String name) {
        if (this.name.equals(name)) {
            setStarted(false);
        }
    }

    public void assignIfStarted(String data, LevelHolder level) {
        if (level.isLevel(levelValue) && started) {
            setValue(data);
        }
    }

    @Override
    public String getName() {
        return name;
    }


}
