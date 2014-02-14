package ru.sidvi.depextractor;

import ru.sidvi.depextractor.commands.Command;
import ru.sidvi.depextractor.commands.CommandFactory;

/**
 * Created by sidvi on 05.02.14.
 */
public class Main {
    public static void main(String[] args) {
        Command command = CommandFactory.create(args);
        command.execute();
        System.out.println(command.getResult());
    }
}
