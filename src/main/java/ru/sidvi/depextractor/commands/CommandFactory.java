package ru.sidvi.depextractor.commands;

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
            if (!dir.isDirectory()) {
                new FailCommand(String.format("Sorry, '%s' is not a directory. Try again.", dir.getAbsolutePath()));
            }
            return new TablePrintCommand(dir);

        }

        return new HelpCommand();
    }

    private static boolean isTablePrintCommand(String[] args) {
        return args.length == 1 && new File(args[0]).isDirectory();
    }
}
