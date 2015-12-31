package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.extractors.JarInfo;
import ru.sidvi.depextractor.DirectoryUtils;
import ru.sidvi.depextractor.formatters.Formatter;
import ru.sidvi.depextractor.processors.Processor;
import ru.sidvi.depextractor.processors.ProcessorBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ��������� ����������������� ������ �� ������ ���������� �� Processor.
 */
public class FormattedOutputCommand extends ResultHolder {

    private File dir;
    private Formatter formatter;
    private ProcessorBuilder builder;


    FormattedOutputCommand(Formatter formatter, ProcessorBuilder builder, String dir) {
        this.formatter = formatter;
        this.builder = builder;
        this.dir = new File(dir);
    }

    public void execute() {
        List<File> jars = DirectoryUtils.list(dir, ".jar");
        List<JarInfo> jarsInfo = extract(jars);

        result = build(jarsInfo, formatter);
        result += "\r\n";
        result += "Processed files: " + jars.size();
    }

    private List<JarInfo> extract(List<File> jars) {
        List<JarInfo> jarsInfo = new ArrayList<JarInfo>();

        for (File jar : jars) {
            Processor processor = builder.setPath(jar.getAbsolutePath()).build();
            processor.extract();
            jarsInfo.addAll(processor.getInfos());
        }
        return jarsInfo;
    }

    private String build(List<JarInfo> jarsInfo, Formatter formatter) {
        StringBuilder builder = new StringBuilder();
        for (JarInfo info : jarsInfo) {
            builder.append(formatter.format(info));
        }

        return builder.toString();
    }
}
