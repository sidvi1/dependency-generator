package ru.sidvi.depextractor;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sidvi on 04.02.14.
 */
class PomParser {
    private InputStream is;
    private String version;
    private String artifactId;
    private String groupId;

    PomParser(InputStream is) {
        this.is = is;
    }

    public String getVersion() {
        return version;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public PomParser parse() {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            version = parseTag(document, "version");
            artifactId = parseTag(document, "artifactId");
            groupId = parseTag(document, "groupId");
        } catch (Exception ignored) {
        }
        return this;
    }

    private String parseTag(Document document, String tag) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        XPathExpression expression = XPathFactory.newInstance().newXPath().compile("//project/" + tag);
        return expression.evaluate(document);
    }

}
