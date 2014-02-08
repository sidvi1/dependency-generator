package ru.sidvi.depextractor;

/**
 * Created by sidvi on 08.02.14.
 */
public class ElementFactory {

    public static BaseElement createVersion(){
        return new Version();
    }

    public static BaseElement createGroup(){
        return new Group();
    }

    public static BaseElement createArtifact(){
        return new Artifact();
    }


}
