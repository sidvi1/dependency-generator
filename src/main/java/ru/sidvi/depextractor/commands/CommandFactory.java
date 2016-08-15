package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.formatters.TableFormatter;
import ru.sidvi.depextractor.validators.ArgsCountValidator;
import ru.sidvi.depextractor.validators.ChainValidator;
import ru.sidvi.depextractor.validators.DirectoryValidator;
import ru.sidvi.depextractor.validators.Validator;

/**
 * Управляет созданием Command.
 */
public class CommandFactory {

    private CommandFactory() {

    }

    public static Command create(String[] args) {
        Command result;

        Validator v = new ChainValidator()
                .add(new ArgsCountValidator())
                .add(new DirectoryValidator());

        if (!v.validate(args)) {
            String message = v.getMessage();
            result = getFailCommand(message);
        } else {
            result = new FormattedOutputCommand(new TableFormatter(), args[0]);
        }

        return result;
    }

    private static CompoundCommand getFailCommand(String message) {
        return new CompoundCommand()
                .add(new FailCommand(message))
                .add(new HelpCommand());
    }
}

