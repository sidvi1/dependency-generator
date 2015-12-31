package ru.sidvi.depextractor;

import ru.sidvi.depextractor.commands.*;

/**
 * Created by Vitaly A. Sidorov on 05.02.14.
 */
public class Main {

    public static void main(String[] args) {
        Command command = CommandFactory.create(args);
        command.execute();
        System.out.println(command.getResult());
    }

}
