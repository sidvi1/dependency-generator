package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.extractors.ManifestExtractor;
import ru.sidvi.depextractor.extractors.PomExtractor;
import ru.sidvi.depextractor.extractors.PomParser;
import ru.sidvi.depextractor.formatters.InlineFormatter;
import ru.sidvi.depextractor.pathcomparators.ManifestPathComparator;
import ru.sidvi.depextractor.pathcomparators.PomPathComparator;
import ru.sidvi.depextractor.processors.JarProcessor;
import ru.sidvi.depextractor.processors.ProcessorBuilder;

import java.io.File;

/**
 * Created by sidvi on 08.02.14.
 */
public abstract class CommandFactory {
    public static Command create(String[] args) {

        if (args.length == 0) {
            return new CompoundCommand()
                    .add(new FailCommand("Too low arguments.."))
                    .add(new HelpCommand());
        }

        if (isTablePrintCommand(args)) {
            File dir = new File(args[0]);
            if(!dir.exists()){
                return new FailCommand(String.format("Sorry, '%s' not exists. Try again.", dir.getAbsolutePath()));
            }
            if (!dir.isDirectory()) {
                return new FailCommand(String.format("Sorry, '%s' is not a directory. Try again.", dir.getAbsolutePath()));
            }
            ProcessorBuilder builder = new JarProcessor.Builder()
                    .addExtractor(new PomPathComparator(), new PomExtractor(new PomParser()))
                    .addExtractor(new ManifestPathComparator(), new ManifestExtractor());

            return new FormattedOutputCommand(dir, new InlineFormatter(), builder);

        }

        return new HelpCommand();
    }

    private static boolean isTablePrintCommand(String[] args) {
        return args.length == 1;
    }
}
