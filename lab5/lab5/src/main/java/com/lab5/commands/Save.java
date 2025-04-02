package com.lab5.commands;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lab5.repository.Repository;

public class Save implements Command {
    @Override
    @SuppressWarnings("UseSpecificCatch")
    public void execute(Repository repository, String... args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: save <filename.json>");
        }

        String filename = args[0];
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            mapper.writeValue(new File(filename), repository.getAll());
            System.out.println("repo saved to " + filename);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String info() {
        return "save <filename.json> - save repository to json file";
    }
}