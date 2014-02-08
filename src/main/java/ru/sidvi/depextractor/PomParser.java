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

    public PomParser parse(InputStream is) {

        Document document = prepareDocument(is);

        if (document != null) {
            version = parseTag(document, "//project/version");
            artifactId = parseTag(document, "//project/artifactId");
            groupId = parseTag(document, "//project/groupId");

            parentVersion = parseTag(document, "//project/parent/version");
            parentArtifactId = parseTag(document, "//project/parent/artifactId");
            parentGroupId = parseTag(document, "//project/parent/groupId");

        }

        return this;
    }

    private Document prepareDocument(InputStream is) {
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
