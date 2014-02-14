package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.JarInfo;
import ru.sidvi.depextractor.Utils;
import ru.sidvi.depextractor.extractors.ManifestExtractor;
import ru.sidvi.depextractor.extractors.PomExtractor;
import ru.sidvi.depextractor.extractors.PomParser;
import ru.sidvi.depextractor.formatters.Formatter;
import ru.sidvi.depextractor.formatters.InlineFormatter;
import ru.sidvi.depextractor.pathcomparators.ManifestPathComparator;
import ru.sidvi.depextractor.pathcomparators.PomPathComparator;
import ru.sidvi.depextractor.processors.JarProcessor;
import ru.sidvi.depextractor.processors.Processor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidvi on 08.02.14.
 */
public class TablePrintCommand extends ResultHolder implements Command {

    private File dir;

    public TablePrintCommand(File dir) {
        this.dir = dir;
    }

    @Override
    public void execute() {
        File[] jars = list(dir);
        List<JarInfo> jarsInfo = extract(jars);

        Formatter formatter = new InlineFormatter();
        result = build(jarsInfo, formatter);
        result += "\n";
        result += "Processed files: " + jars.length;
    }

    private static String build(List<JarInfo> jarsInfo, Formatter formatter) {
        StringBuilder builder = new StringBuilder();
        for (JarInfo info : jarsInfo) {
            builder.append(formatter.format(info));
        }

        return builder.toString();
    }

    private static List<JarInfo> extract(File[] jars) {
        List<JarInfo> jarsInfo = new ArrayList<JarInfo>();

        for (File jar : jars) {
            Processor processor = new JarProcessor.Builder().setPath(jar.getAbsolutePath())
                    .addExtractor(new PomPathComparator(), new PomExtractor(new PomParser()))
                    .addExtractor(new ManifestPathComparator(), new ManifestExtractor())
                    .build();
            processor.extract();
            jarsInfo.addAll(processor.getInfos());
        }
        return jarsInfo;
    }

    private static File[] list(File dir) {
        return Utils.list(dir, ".jar");
    }
}
