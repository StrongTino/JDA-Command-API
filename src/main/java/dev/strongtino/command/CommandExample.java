package dev.strongtino.command;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.Arrays;

public class CommandExample extends Command {

    // We are extending "Command" class therefore we need to fill parameters from one of the constructors of the super class
    public CommandExample() {
        super("example", Arrays.asList("example1", "example2"), Permission.ADMINISTRATOR);
    }

    @Override
    public void execute(TextChannel channel, User author, Message message, String[] args) {
        // If player executes command (prefix) + "example", "example1" or "example2" and has permission "ADMINISTRATOR", code below will run:
        channel.sendMessage("Hello " + author.getAsMention() + "!").queue();
    }
}
