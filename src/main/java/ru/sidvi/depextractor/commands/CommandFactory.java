package ru.sidvi.depextractor.commands;

import ru.sidvi.depextractor.formatters.InlineFormatter;
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

        return new FormattedOutputCommand(new InlineFormatter(), args[0]);
    }
}
