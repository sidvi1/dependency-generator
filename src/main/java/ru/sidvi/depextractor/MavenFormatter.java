package ru.sidvi.depextractor;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by sidvi on 05.02.14.
 */
public class MavenFormatter {

    public String format(Info info) {
        StringWriter writer = new StringWriter();

        PrintWriter printer = new PrintWriter(writer);
        printer.println(FormatterUtils.pad(1) + "<dependency>");
//        printer.println(FormatterUtils.pad(2) + FormatterUtils.tag("groupId", info.getArtifact().getGroup()));
//        printer.println(FormatterUtils.pad(2) + FormatterUtils.tag("artifactId", info.getArtifact().getArtifact()));
//        printer.println(FormatterUtils.pad(2) + FormatterUtils.tag("version", info.getVersion().getValue()));
        printer.println(FormatterUtils.pad(1) + "</dependency>");

        return writer.toString();
    }
}
