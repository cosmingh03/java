package com.lab5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.lab5.commands.Add;
import com.lab5.commands.Command;
import com.lab5.commands.Exit;
import com.lab5.commands.Help;
import com.lab5.commands.Load;
import com.lab5.commands.Remove;
import com.lab5.commands.Report;
import com.lab5.commands.Save;
import com.lab5.commands.Update;
import com.lab5.repository.Repository;

public class Shell {
    private final Repository repository;
    private final Map<String, Command> commands;

    public Shell() {
        this.repository = new Repository();
        this.commands = new HashMap<>();
        initCommands();
    }

    private void initCommands() {
        commands.put("add", new Add());
        commands.put("remove", new Remove());
        commands.put("update", new Update());
        commands.put("load", new Load());
        commands.put("save", new Save());
        commands.put("report", new Report());
        commands.put("help", new Help(commands));
        commands.put("exit", new Exit());
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("image repo shell");
            System.out.println("help for available commands");

            while (true) {
                System.out.print("> ");
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                processCommand(line);
            }
        }
    }

    private void processCommand(String line) {
        try {
            String[] parts = line.split("\\s+");
            String commandName = parts[0].toLowerCase();

            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);

            Command command = commands.get(commandName);
            if (command != null) {
                command.execute(repository, args);
            } else {
                System.out.println("Unknown command. Type 'help' for available commands.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }
}
