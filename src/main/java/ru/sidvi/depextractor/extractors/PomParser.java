package ru.sidvi.depextractor.extractors;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Vitaly A. Sidorov on 04.02.14.
 */
public class PomParser {

    private String version;
    private String artifactId;
    private String groupId;
    private String parentVersion;
    private String parentArtifactId;
    private String parentGroupId;

    public String getVersion() {
        return version;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getParentVersion() {
        return parentVersion;
    }

    public String getParentArtifactId() {
        return parentArtifactId;
    }

    public String getParentGroupId() {
        return parentGroupId;
    }

    public void parse(InputStream is) {


        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader r =
                null;
        try {
            r = factory.createXMLEventReader(new InputStreamReader(is));
        } catch (XMLStreamException e) {

        }
        boolean isParent = false;
        boolean isParentGroup = false;
        boolean isParentArtifact = false;
        boolean isParentVersion = false;
        while (r.hasNext()) {
            XMLEvent e = null;
            try {
                e = r.nextEvent();
            } catch (XMLStreamException ignored) {
            }

            switch (e.getEventType()) {
                case XMLStreamConstants.START_ELEMENT: {
                    StartElement el = e.asStartElement();
                    if (el.getName().getLocalPart().equals("parent")) {
                        isParent = true;
                    }
                    if (isParent && el.getName().getLocalPart().equals("groupId")) {
                        isParentGroup = true;
                    }
                    if (isParent && el.getName().getLocalPart().equals("artifactId")) {
                        isParentArtifact = true;
                    }
                    if (isParent && el.getName().getLocalPart().equals("version")) {
                        isParentVersion = true;
                    }
                }
                break;
                case XMLStreamConstants.CHARACTERS: {
                    if (isParentGroup && e.getEventType() == XMLStreamConstants.CHARACTERS) {
                        parentGroupId = e.asCharacters().getData();
                    }
                    if (isParentArtifact && e.getEventType() == XMLStreamConstants.CHARACTERS) {
                        parentArtifactId = e.asCharacters().getData();
                    }
                    if (isParentVersion && e.getEventType() == XMLStreamConstants.CHARACTERS) {
                        parentVersion = e.asCharacters().getData();
                    }
                }
                break;
                case XMLStreamConstants.END_ELEMENT: {
                    EndElement el = e.asEndElement();
                    if (el.getName().getLocalPart().equals("parent")) {
                        isParent = false;
                    }
                    if (isParent && el.getName().getLocalPart().equals("groupId")) {
                        isParentGroup = false;
                    }
                    if (isParent && el.getName().getLocalPart().equals("artifactId")) {
                        isParentArtifact = false;
                    }
                    if (isParent && el.getName().getLocalPart().equals("version")) {
                        isParentVersion = false;
                    }
                }
                break;
            }
        }
    }
}
