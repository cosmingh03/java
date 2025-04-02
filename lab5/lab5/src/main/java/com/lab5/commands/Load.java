package com.lab5.commands;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lab5.Image.Image;
import com.lab5.repository.Repository;

public class Load implements Command {
    @Override
    public String info() {
        return "load <filename.json> - load repo from json";
    }

    @Override
    @SuppressWarnings("UseSpecificCatch")
    public void execute(Repository repository, String... args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: load <filename.json>");
        }

        String filename = args[0];
        File file = new File(filename);

        if (!file.exists()) {
            System.err.println("File not found: " + filename);
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            List<Image> images = mapper.readValue(
                    file,
                    new TypeReference<List<Image>>() {
                    });

            int count = 0;
            for (Image image : images) {
                if (repository.add(image)) {
                    count++;
                }
            }

            System.out.println("Loaded " + count + " images from " + filename);

        } catch (Exception e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }
}