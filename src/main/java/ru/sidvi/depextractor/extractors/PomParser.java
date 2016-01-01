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

        Tag parent = new Tag("parent");
        Tag parentGroupId = new Tag("groupId");
        Tag parentArtifactId = new Tag("artifactId");
        Tag parentVersion = new Tag("version");
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

                    parent.checkForStart(tagName);
                    if (parent.isStarted()) {
                        parentGroupId.checkForStart(tagName);
                        parentArtifactId.checkForStart(tagName);
                        parentVersion.checkForStart(tagName);
                    }
                }
                break;
                case XMLStreamConstants.CHARACTERS: {
                    parentGroupId.assignIfStarted(e.asCharacters().getData());
                    parentArtifactId.assignIfStarted(e.asCharacters().getData());
                    parentVersion.assignIfStarted(e.asCharacters().getData());
                }
                break;
                case XMLStreamConstants.END_ELEMENT: {
                    EndElement el = e.asEndElement();
                    String tagName = el.getName().getLocalPart();

                    if (parent.isStarted()) {
                        parentGroupId.checkForEnd(tagName);
                        parentArtifactId.checkForEnd(tagName);
                        parentVersion.checkForEnd(tagName);
                    }
                    parent.checkForEnd(tagName);
                }
                break;
            }
        }
        this.parentGroupId = parentGroupId.getValue();
        this.parentArtifactId = parentArtifactId.getValue();
        this.parentVersion = parentVersion.getValue();
    }

    private class Tag {
        private boolean started;
        private String name;
        private String value = "";

        public Tag(String name) {
            this.name = name;
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

        public void assignIfStarted(String data) {
            if(started){
                setValue(data);
            }
        }

    }
}
