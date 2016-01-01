package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.DirectoryUtils;
import ru.sidvi.depextractor.formatters.Formatter;
import ru.sidvi.depextractor.extractors.InfoExtractorFacade;
import ru.sidvi.depextractor.model.JarInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Формирует отформатированную строку из данных полученных от Processor.
 */
public class FormattedOutputCommand extends ResultHolder {

    private File dir;
    private Formatter formatter;

    FormattedOutputCommand(Formatter formatter, String dir) {
        this.formatter = formatter;
        this.dir = new File(dir);
    }

    public void execute() {
        List<File> jars = DirectoryUtils.list(dir, ".jar");
        List<JarInfo> jarsInfo = extract(jars);

        result = format(jarsInfo, formatter);
        result += "\r\n";
        result += "Processed files: " + jars.size();
    }

    private List<JarInfo> extract(List<File> jars) {
        List<JarInfo> jarsInfo = new ArrayList<JarInfo>();

        for (File jar : jars) {
            InfoExtractorFacade processor = new InfoExtractorFacade(jar.getAbsolutePath());
            jarsInfo.addAll(processor.extract());
        }
        return jarsInfo;
    }

    private String format(List<JarInfo> jarsInfo, Formatter formatter) {
        StringBuilder builder = new StringBuilder();
        for (JarInfo info : jarsInfo) {
            builder.append(formatter.format(info));
        }

        return builder.toString();
    }
}
