package ru.sidvi.depextractor;

import ru.sidvi.depextractor.commands.*;
import ru.sidvi.depextractor.extractors.ManifestExtractorFactory;
import ru.sidvi.depextractor.extractors.PomExtractorFactory;
import ru.sidvi.depextractor.formatters.Formatter;
import ru.sidvi.depextractor.formatters.InlineFormatter;
import ru.sidvi.depextractor.pathcomparators.ManifestPathComparator;
import ru.sidvi.depextractor.pathcomparators.PomPathComparator;
import ru.sidvi.depextractor.processors.JarProcessor;
import ru.sidvi.depextractor.processors.ProcessorBuilder;

import java.io.File;

/**
 * Created by Vitaly A. Sidorov on 05.02.14.
 */
public class Main {

    private final static ProcessorBuilder builder = new JarProcessor.Builder()
            .addExtractor(new PomPathComparator(), new PomExtractorFactory())
            .addExtractor(new ManifestPathComparator(), new ManifestExtractorFactory());
    private final static Formatter formatter = new InlineFormatter();
    private final static Command help = new HelpCommand();
    private final static FormattedOutputCommand tableOutput = new FormattedOutputCommand(formatter, builder);
    private final static Command fail = new FailCommand();

    public static void main(String[] args) {
        Command command = create(args);
        command.execute();
        System.out.println(command.getResult());
    }

    public static Command create(String[] args) {

        if (args.length == 0) {
            fail.setMessage("Too low arguments..");
            CompoundCommand complexFail = new CompoundCommand()
                    .add(fail)
                    .add(help);
            return complexFail;
        }

        if (isTablePrintCommand(args)) {
            File dir = new File(args[0]);
            String path = dir.getAbsolutePath();
            if (!dir.exists()) {
                fail.setMessage(String.format("Sorry, '%s' not exists. Try again.", path));
                return fail;
            }
            if (!dir.isDirectory()) {
                fail.setMessage(String.format("Sorry, '%s' is not a directory. Try again.", path));
                return fail;
            }


            tableOutput.setJarsDirectory(dir);
            return tableOutput;

        }

        return help;
    }

    private static boolean isTablePrintCommand(String[] args) {
        return args.length == 1;
    }
}
