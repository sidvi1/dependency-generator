package ru.sidvi.depextractor;

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
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sidvi on 05.02.14.
 */
public class Main {

    static int count = 0;
    static Map<String, String> notFullyProcessed = new HashMap<String, String>();

    static String processDirectory(String path) {
        File[] jars = listJars(path);
        count = jars.length;
        List<JarInfo> jarsInfo = extractInfo(jars);

        Formatter formatter = new InlineFormatter();
        return build(jarsInfo, formatter);
    }

    private static String build(List<JarInfo> jarsInfo, Formatter formatter) {
        StringBuilder builder = new StringBuilder();
        for (JarInfo info : jarsInfo) {
            builder.append(formatter.format(info));
        }

        return builder.toString();
    }

    private static List<JarInfo> extractInfo(File[] jars) {
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

    private static File[] listJars(String path) {
        File dir = new File(path);
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.toLowerCase().endsWith(".jar");
            }
        });
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Укажите путь к jar файлам.");
        }

        System.out.println(processDirectory(args[0]));

        System.out.println("Founded: " + count);

    }
}
