package dev.strongtino.command;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command extends ListenerAdapter {

    private final char PREFIX = '!';

    private final String command;
    private final List<String> aliases;
    private final Permission permission;

    // Main constructor that will initialize every variable
    public Command(String command, List<String> aliases, Permission permission) {
        this.command = command;
        this.aliases = aliases;
        this.permission = permission;
    }

    /***
     * Constructors used if we don't need all parameters.
     * Unused variables are set as null or default value and will not make an impact on the command.
     *
     * Example: If you use the no args constructor you can then have multiple commands in single class by checking the first argument
     */
    public Command() {
        this(null, new ArrayList<>(), null);
    }
    public Command(String command) {
        this(command, new ArrayList<>(), null);
    }
    public Command(String command, List<String> aliases) {
        this(command, aliases, null);
    }
    public Command(String command, Permission permission) {
        this(command, new ArrayList<>(), permission);
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // We usually don't want bots being able to execute commands.
        if (event.getAuthor().isBot()) return;

        // Getting the message content and splitting it into the array
        String[] arguments = event.getMessage().getContentRaw().split(" ");

        /*
         *  Making a new array without the first element (command)
         *
         *  Example Command: /ban <player> <reason>
         *  args[0] -> <player>
         *  args[1] -> <reason>
         *  etc...
         */
        String[] args = Arrays.copyOfRange(arguments, command == null ? 0 : 1, arguments.length);

        // If permission is set and user doesn't have that permission, command won't be executed.
        if (permission != null && !event.getMember().hasPermission(permission)) return;

        // If all parameters are valid -> execute the command
        if (command == null || arguments[0].equalsIgnoreCase(PREFIX + command) || (!aliases.isEmpty() && aliases.stream().anyMatch(alias -> arguments[0].equalsIgnoreCase(PREFIX + alias)))) {
            execute(event.getChannel(), event.getAuthor(), event.getMessage(), args);
        }
    }

    // Basic abstract method, you can add more parameters or remove some if you want (personal preference)
    public abstract void execute(TextChannel channel, User author, Message message, String[] args);
}
