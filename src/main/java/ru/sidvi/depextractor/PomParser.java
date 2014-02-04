package ru.sidvi.depextractor;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
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

        Document document = prepareDocument();

        if (document != null) {
            version = guessTag(document, "version");
            artifactId = guessTag(document, "artifactId");
            groupId = guessTag(document, "groupId");
        }

        return this;
    }

    private String guessTag(Document document, String tag) {
        String ver = parseTag(document, "//project/" + tag);
        if (ver.isEmpty()) {
            ver = parseTag(document, "//project/parent/" + tag);
        }
        System.out.println("parse "+ tag + ", result is " + ver);
        return ver;
    }

    private Document prepareDocument() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        } catch (Exception ignored) {
        }
        return null;
    }

    private String parseTag(Document document, String tag) {
        XPathExpression expression = null;
        try {
            expression = XPathFactory.newInstance().newXPath().compile(tag);
            return expression.evaluate(document).trim();
        } catch (XPathExpressionException ignored) {
        }
        return "";
    }

}
