package ru.sidvi.depextractor.extractors.pom;

/**
 * Хранит информацию об извлекаемом элементе.
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

    @Override
    public boolean isStarted() {
        return started;
    }

    private void setStarted(boolean started) {
        this.started = started;
    }

    @Override
    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

    @Override
    public void checkForStart(String name, LevelHolder level) {
        if (level.isLevel(levelValue) && this.name.equals(name)) {
            setStarted(true);
        }
    }

    @Override
    public void checkForEnd(String name, LevelHolder level) {
        if (level.isLevel(levelValue) && this.name.equals(name)) {
            setStarted(false);
        }
    }

    @Override
    public void assignIfStarted(String data) {
        if (started) {
            setValue(data);
        }
    }

    @Override
    public String getName() {
        return name;
    }


}
