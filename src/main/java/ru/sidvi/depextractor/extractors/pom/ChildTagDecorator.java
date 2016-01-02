package ru.sidvi.depextractor.extractors.pom;

/**
 * Хранит значение текущего тэга и родительского.
 */
class ChildTagDecorator extends SimpleTag {
    private Tag parent;

    public ChildTagDecorator(String name, Tag parent, int levelValue) {
        super(name, levelValue);
        this.parent = parent;
    }

    @Override
    public String getName() {
        return parent.getName() + "." + super.getName();
    }

    @Override
    public void checkForStart(String name, LevelHolder level) {
        if (parent.isStarted()) {
            super.checkForStart(name, level);
        }
    }

    @Override
    public void checkForEnd(String name, LevelHolder level) {
        if (parent.isStarted()) {
            super.checkForEnd(name, level);
        }
    }
}
