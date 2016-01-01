package ru.sidvi.depextractor.extractors.pom;

/**
 * Created by sidvi on 01.01.2016.
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
    public void checkForStart(String name) {
        if (parent.isStarted()) {
            super.checkForStart(name);
        }
    }

    @Override
    public void checkForEnd(String name) {
        if (parent.isStarted()) {
            super.checkForEnd(name);
        }
    }
}
