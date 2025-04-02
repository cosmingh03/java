package com.lab5.commands;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import com.lab5.Image.Image;
import com.lab5.repository.Repository;

public class Update implements Command {
    @Override
    public void execute(Repository repository, String... args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("usage : update <type> <name> <new_value>");
        }

        String type = args[0].toLowerCase();
        String name = args[1];
        String newValue = args[2];

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        Image old = repository.findImageByName(name);
        Image updated;

        switch (type) {
            case "name" -> {
                if (newValue == null || newValue.isBlank()) {
                    throw new IllegalArgumentException("New name cannot be null or blank");
                }
                updated = new Image(newValue, old.date(), old.tags(), old.location());
            }
            case "location" -> {
                if (newValue == null || newValue.isBlank()) {
                    throw new IllegalArgumentException("New location cannot be null or blank");
                }
                updated = new Image(old.name(), old.date(), old.tags(), Path.of(newValue));
            }
            case "date" -> {
                try {
                    LocalDate newDate = LocalDate.parse(newValue);
                    updated = new Image(old.name(), newDate, old.tags(), old.location());
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid date format. Use: YYYY-MM-DD");
                }
            }
            case "tags" -> {
                List<String> newTags = Arrays.asList(newValue.split(","));
                updated = new Image(old.name(), old.date(), newTags, old.location());
            }
            default -> throw new IllegalArgumentException("Invalid update type. Use: name, location, date, or tags");
        }

        repository.remove(name);
        repository.add(updated);

        System.out.println("Updated " + type + " for image: " + name);
    }

    @Override
    public String info() {

        return "update <type> <name> <new_value> - update image properties (type: name, location, date, tags)";

    }
}
