package ru.sidvi.depextractor.extractors.pom;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Парсит pom.xml на предмет необходимых тэгов.
 */
public class PomParser {

    private List<Tag> matchers = new ArrayList<>();

    {
        SimpleTag parent = new SimpleTag("parent", 2);
        matchers.add(parent);
        matchers.add(new ChildTagDecorator("groupId", parent, 3));
        matchers.add(new ChildTagDecorator("artifactId", parent, 3));
        matchers.add(new ChildTagDecorator("version", parent, 3));
        matchers.add(new SimpleTag("groupId", 2));
        matchers.add(new SimpleTag("artifactId", 2));
        matchers.add(new SimpleTag("version", 2));
    }

    public String getVersion() {
        return getValue("version");
    }

    public String getArtifactId() {
        return getValue("artifactId");
    }

    public String getGroupId() {
        return getValue("groupId");
    }

    public String getParentVersion() {
        return getValue("parent.version");
    }

    public String getParentArtifactId() {
        return getValue("parent.artifactId");
    }

    public String getParentGroupId() {
        return getValue("parent.groupId");
    }

    public void parse(InputStream is) {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader r =
                null;
        try {
            r = factory.createXMLEventReader("UTF-8", new InputStreamReader(is,"UTF-8"));
        } catch (XMLStreamException | UnsupportedEncodingException e) {

        }

        LevelHolder level = new LevelHolder();
        while (r.hasNext()) {
            XMLEvent e = null;
            try {
                e = r.nextEvent();
            } catch (XMLStreamException ignored) {
            }

            switch (e.getEventType()) {
                case XMLStreamConstants.START_ELEMENT: {
                    level.up();

                    StartElement el = e.asStartElement();
                    String tagName = el.getName().getLocalPart();

                    for (Tag tag : matchers) {
                        tag.checkForStart(tagName, level);
                    }
                }
                break;
                case XMLStreamConstants.CHARACTERS: {
                    for (Tag tag : matchers) {
                        tag.assignIfStarted(e.asCharacters().getData());
                    }
                }
                break;
                case XMLStreamConstants.END_ELEMENT: {

                    EndElement el = e.asEndElement();
                    String tagName = el.getName().getLocalPart();

                    for (Tag tag : matchers) {
                        tag.checkForEnd(tagName, level);
                    }

                    level.down();
                }
                break;
            }
        }
    }

    private String getValue(String tagName) {
        for (Tag matcher : matchers) {
            if (matcher.getName().equals(tagName)) {
                return matcher.getValue();
            }
        }
        return "";
    }

}
