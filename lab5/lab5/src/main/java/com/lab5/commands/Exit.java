package com.lab5.commands;

import com.lab5.repository.Repository;

public class Exit implements Command {
    @Override
    public void execute(Repository repository, String... args) {
        System.out.println("exiting");
        System.exit(0);
    }

    @Override
    public String info() {
        return "exit - exit the application";
    }
}