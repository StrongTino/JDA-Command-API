package dev.strongtino.command;

import net.dv8tion.jda.core.JDA;

import java.util.HashSet;
import java.util.Set;

public class CommandManager {

    // Example of command registration -> keep in mind that new instance of this class has to be called for commands to be initialized
    public CommandManager(JDA jda) {
        Set<Command> commands = new HashSet<>();

        commands.add(new CommandExample());

        commands.forEach(jda::addEventListener);
    }
}
