package com.lab5.commands;

import com.lab5.Image.Image;
import com.lab5.repository.Repository;

public class Add implements Command {
    @Override
    public void execute(Repository repository, String... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("usage : add <name> <location>");
        }
        String name = args[0];
        String location = args[1];
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or blank");
        }
        if (repository.getAll().stream().anyMatch(img -> img.name().equals(name))) {
            throw new IllegalArgumentException("Image with this name already exists");
        }

        Image image = new Image(name, location);
        repository.add(image);
    }

    @Override
    public String info() {
        return "add <name> <location> - add a new image";
    }

}
