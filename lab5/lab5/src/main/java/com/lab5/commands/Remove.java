package com.lab5.commands;

import com.lab5.repository.Repository;

public class Remove implements Command {
    @Override
    public void execute(Repository repository, String... args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("usage : remove <name>");
        }
        String name = args[0];
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (!repository.getAll().stream().anyMatch(img -> img.name().equals(name))) {
            throw new IllegalArgumentException("Image with this name does not exist");
        }

        repository.remove(name);
    }

    @Override
    public String info() {
        return "remove <name> -remove a image";
    }

}
