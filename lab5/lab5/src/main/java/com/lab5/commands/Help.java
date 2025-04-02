package com.lab5.commands;

import java.util.Map;

import com.lab5.repository.Repository;

public class Help implements Command {
    private final Map<String, Command> commands;

    public Help(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(Repository repository, String... args) {
        if (args.length > 0) {
            String commandName = args[0].toLowerCase();
            Command command = commands.get(commandName);
            if (command != null) {
                System.out.println("\nhelp for '" + commandName);
                System.out.println(command.info());
            } else {
                System.out.println("unknown " + commandName);
            }
        } else {
            System.out.println("\ncommands:");
            commands.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> System.out.println(entry.getValue().info()));
        }
        System.out.println();
    }

    @Override
    public String info() {
        return "help [command] - infos";
    }
}