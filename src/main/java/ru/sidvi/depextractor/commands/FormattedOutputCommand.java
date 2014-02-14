package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.JarInfo;
import ru.sidvi.depextractor.Utils;
import ru.sidvi.depextractor.formatters.Formatter;
import ru.sidvi.depextractor.processors.JarProcessor;
import ru.sidvi.depextractor.processors.Processor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidvi on 08.02.14.
 */
public class FormattedOutputCommand extends ResultHolder {

    private File dir;
    private Formatter formatter;
    private JarProcessor.Builder builder;


    public FormattedOutputCommand(File jarsDir, Formatter formatter, JarProcessor.Builder builder) {
        this.dir = jarsDir;
        this.formatter = formatter;
        this.builder = builder;
    }

    private static File[] list(File dir) {
        return Utils.list(dir, ".jar");
    }

    @Override
    public void execute() {
        File[] jars = list(dir);
        List<JarInfo> jarsInfo = extract(jars);

        result = build(jarsInfo, formatter);
        result += "\n";
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
