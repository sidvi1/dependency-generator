package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.formatters.TableFormatter;
import ru.sidvi.depextractor.validators.ArgsCountValidator;
import ru.sidvi.depextractor.validators.ChainValidator;
import ru.sidvi.depextractor.validators.DirectoryValidator;
import ru.sidvi.depextractor.validators.Validator;

import java.io.File;

/**
 * Управляет созданием Command.
 */
public class CommandFactory {

    private CommandFactory() {

    }

    public static Command create(String[] args) {
        Command help = new HelpCommand();
        Validator v = new ChainValidator()
                .add(new ArgsCountValidator(args))
                .add(new DirectoryValidator( new File(args[0])));
        if (!v.validate()) {
            return new CompoundCommand()
                    .add(new FailCommand(v.getMessage()))
                    .add(help);
        }

        return new FormattedOutputCommand(new TableFormatter(), args[0]);
    }
}

