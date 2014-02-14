package ru.sidvi.depextractor.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidvi on 14.02.14.
 */
public class CompoundCommand extends ResultHolder implements Command {

    private List<Command> commands = new ArrayList<Command>();

    public CompoundCommand add(Command command) {
        commands.add(command);
        return this;
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
            result += command.getResult() + "\n";
        }
    }
}
