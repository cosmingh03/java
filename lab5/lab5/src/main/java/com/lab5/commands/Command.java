package com.lab5.commands;

import com.lab5.repository.Repository;

public interface Command {
    void execute(Repository repository, String... args);

    String info();
}