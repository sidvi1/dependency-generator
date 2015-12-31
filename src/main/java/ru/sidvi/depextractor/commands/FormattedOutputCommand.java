package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.JarInfo;
import ru.sidvi.depextractor.Utils;
import ru.sidvi.depextractor.formatters.Formatter;
import ru.sidvi.depextractor.processors.Processor;
import ru.sidvi.depextractor.processors.ProcessorBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaly A. Sidorov on 08.02.14.
 */
public class FormattedOutputCommand extends ResultHolder {

    private File dir;
    private Formatter formatter;
    private ProcessorBuilder builder;


    public FormattedOutputCommand(Formatter formatter, ProcessorBuilder builder) {
        this.formatter = formatter;
        this.builder = builder;
    }

    public void setJarsDirectory(File dir) {
        this.dir = dir;
    }

    private static File[] list(File dir) {
        return Utils.list(dir, ".jar");
    }

    public void execute() {
        File[] jars = list(dir);
        List<JarInfo> jarsInfo = extract(jars);

        result = build(jarsInfo, formatter);
        result += "\r\n";
        result += "Processed files: " + jars.length;
    }

    private String build(List<JarInfo> jarsInfo, Formatter formatter) {
        StringBuilder builder = new StringBuilder();
        for (JarInfo info : jarsInfo) {
            builder.append(formatter.format(info));
        }

        return builder.toString();
    }

    private List<JarInfo> extract(File[] jars) {
        List<JarInfo> jarsInfo = new ArrayList<JarInfo>();

        for (File jar : jars) {
            Processor processor = builder.setPath(jar.getAbsolutePath()).build();
            processor.extract();
            jarsInfo.addAll(processor.getInfos());
        }
        return jarsInfo;
    }
}
