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
import java.util.ArrayList;
import java.util.List;

/**
 * Парсит pom.xml на предмент необходимых тэгов.
 */
public class PomParser {

    private List<Tag> matchers = new ArrayList<>();

    {
        SimpleTag parent = new SimpleTag("parent");
        matchers.add(parent);
        matchers.add(new ChildTagDecorator("groupId", parent));
        matchers.add(new ChildTagDecorator("artifactId", parent));
        matchers.add(new ChildTagDecorator("version", parent));
        matchers.add(new SimpleTag("groupId"));
        matchers.add(new SimpleTag("artifactId"));
        matchers.add(new SimpleTag("version"));
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
            r = factory.createXMLEventReader(new InputStreamReader(is));
        } catch (XMLStreamException e) {

        }

        while (r.hasNext()) {
            XMLEvent e = null;
            try {
                e = r.nextEvent();
            } catch (XMLStreamException ignored) {
            }

            switch (e.getEventType()) {
                case XMLStreamConstants.START_ELEMENT: {
                    StartElement el = e.asStartElement();
                    String tagName = el.getName().getLocalPart();

                    for (Tag tag : matchers) {
                        tag.checkForStart(tagName);
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
                        tag.checkForEnd(tagName);
                    }
                }
                break;
            }
        }
    }

    private String getValue(String tagName) {
        for (Tag matcher : matchers) {
            if(matcher.getName().equals(tagName)){
                return matcher.getValue();
            }
        }
        return "";
    }
}
