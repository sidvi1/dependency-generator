package ru.sidvi.depextractor.extractors.pom;

/**
 * Хранит значение уровня xml на котором находится извлекаемый элемент.
 */
class LevelHolder {
    private int level;

    public void down() {
        level--;
    }

    public void up() {
        level++;
    }

    public boolean isLevel(int level) {
        return this.level == level;
    }

    @Override
    public String toString() {
        return "" + level;
    }
}
