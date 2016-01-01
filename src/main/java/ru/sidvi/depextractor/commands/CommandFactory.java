package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.extractors.ManifestExtractorFactory;
import ru.sidvi.depextractor.extractors.PomExtractorFactory;
import ru.sidvi.depextractor.formatters.Formatter;
import ru.sidvi.depextractor.formatters.InlineFormatter;
import ru.sidvi.depextractor.pathcomparators.ManifestPathComparator;
import ru.sidvi.depextractor.pathcomparators.PomPathComparator;
import ru.sidvi.depextractor.processors.InfoExtractorFacade;
import ru.sidvi.depextractor.validators.ArgsCountValidator;
import ru.sidvi.depextractor.validators.ChainValidator;
import ru.sidvi.depextractor.validators.DirectoryValidator;
import ru.sidvi.depextractor.validators.Validator;

/**
 * ”правл€ет созданием Command.
 */
public class CommandFactory {

    private CommandFactory() {

    }

    public static Command create(String[] args) {
        Command help = new HelpCommand();
        Validator v = new ChainValidator(new Validator[]{new ArgsCountValidator(args), new DirectoryValidator(args)});
        if (!v.validate()) {
            return new CompoundCommand()
                    .add(new FailCommand(v.getMessage()))
                    .add(help);
        }

        InfoExtractorFacade.Builder builder = new InfoExtractorFacade.Builder()
                .addExtractor(new PomPathComparator(), new PomExtractorFactory())
                .addExtractor(new ManifestPathComparator(), new ManifestExtractorFactory());

        Formatter formatter = new InlineFormatter();
        return new FormattedOutputCommand(formatter, builder, args[0]);
    }
}
