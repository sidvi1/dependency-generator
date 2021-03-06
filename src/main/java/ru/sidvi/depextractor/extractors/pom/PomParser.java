package ru.sidvi.depextractor.extractors.pom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Парсит pom.xml на предмет необходимых тэгов.
 */
public class PomParser {

    ExtractionHolder holder = new ExtractionHolder();
    private Logger logger = LoggerFactory.getLogger(PomParser.class);

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

        try (InputStreamReader reader = new InputStreamReader(is, "UTF-8")) {

            XMLEventReader r = factory.createXMLEventReader("UTF-8", reader);
            while (r.hasNext()) {
                XMLEvent e = r.nextEvent();
                actionForEvent(e, e.getEventType());
            }
        } catch (XMLStreamException | IOException e) {
            logger.error("Error while parsing.", e);
        }
    }

    private void actionForEvent(XMLEvent e, int eventType) {
        switch (eventType) {
            case XMLStreamConstants.START_ELEMENT: {
                holder.startElement(e);
            }
            break;
            case XMLStreamConstants.CHARACTERS: {
                holder.characters(e);
            }
            break;
            case XMLStreamConstants.END_ELEMENT: {
                holder.endElement(e);
            }
            break;
        }
    }

    private String getValue(String tagName) {
        return holder.getValue(tagName);
    }

    private static class ExtractionHolder {
        private LevelHolder level = new LevelHolder();

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

        private void characters(XMLEvent e) {
            for (Tag tag : matchers) {
                tag.assignIfStarted(e.asCharacters().getData());
            }
        }

        private void endElement(XMLEvent e) {
            EndElement el = e.asEndElement();
            String tagName = el.getName().getLocalPart();

            for (Tag tag : matchers) {
                tag.checkForEnd(tagName, level);
            }

            level.down();
        }

        private void startElement(XMLEvent e) {
            level.up();

            StartElement el = e.asStartElement();
            String tagName = el.getName().getLocalPart();

            for (Tag tag : matchers) {
                tag.checkForStart(tagName, level);
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
}
