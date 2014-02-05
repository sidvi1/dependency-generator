package ru.sidvi.depextractor;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by sidvi on 05.02.14.
 */
public class MavenFormatter implements InfoFormatter {

    @Override
    public String format(Info info) {
        StringWriter writer = new StringWriter();

        PrintWriter printer = new PrintWriter(writer);
        printer.println(pad(4) + "<dependency>");
        printer.println(pad(8) + tag("groupId", info.getArtifact().getGroup()));
        printer.println(pad(8) + tag("artifactId", info.getArtifact().getArtifact()));
        printer.println(pad(8) + tag("version", info.getVersion().getValue()));
        printer.println(pad(4) + "</dependency>");

        return writer.toString();
    }

    private String pad(int padding) {
        String result = "";
        for (int i = 0; i < padding; i++) {
            result += " ";
        }
        return result;
    }

    private String tag(String name, String value) {
        return "<" + name + ">" + value + "</" + name + ">";
    }

}
