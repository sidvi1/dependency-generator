package ru.sidvi.depextractor;

import ru.sidvi.depextractor.commands.*;
import ru.sidvi.depextractor.formatters.CsvFormatter;
import ru.sidvi.depextractor.formatters.TableFormatter;
import ru.sidvi.depextractor.validators.*;

import java.io.File;
import java.io.PrintStream;

public class Main {

    public static Validator v = new ChainValidator()
            .add(new ArgsCountValidator())
            .add(new DirectoryValidator());
    public static PrintStream out = System.out;

    public static void main(String[] args) {
        out.println(new Main().doJob(args));
    }

    String doJob(String[] args) {
        Command result = getCommand(args);
        result.execute();
        return result.getResult();
    }

    private Command getCommand(String[] args) {
        Command result;
        if (!v.validate(args)) {
            String message = v.getMessage();
            result = getFailCommand(message);
        } else if (new CsvArgumentValidator().validate(args)) {
            result = getCsvOutput(args);
        } else {
            result = getDefaultOutput(args);
        }
        return result;
    }

    private Command getCsvOutput(String[] args) {
        return new FormattedOutputCommand(new CsvFormatter(";"), DirectoryUtils.list(new File(args[0]), ".jar"));
    }

    private FormattedOutputCommand getDefaultOutput(String[] args) {
        return new FormattedOutputCommand(new TableFormatter(), DirectoryUtils.list(new File(args[0]), ".jar"));
    }

    private CompoundCommand getFailCommand(String message) {
        return new CompoundCommand()
                .add(new FailCommand(message))
                .add(new HelpCommand());
    }
}
