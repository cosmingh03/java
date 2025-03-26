package com.lab5.Image;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lab5.Errors.InvalidImageException;

public record Image(
        String name,
        LocalDate date,
        List<String> tags,
        Path location) {

    public Image {
        if (name == null || name.isBlank()) {
            throw new InvalidImageException("invalid name");
        }
        if (date == null) {
            throw new InvalidImageException("invalid date");
        }
        if (location == null) {
            throw new InvalidImageException("invalid location");
        }

        tags = tags == null ? List.of() : Collections.unmodifiableList(new ArrayList<>(tags));
    }

    public Image(String name, String location) {
        this(name, LocalDate.now(), List.of(), Path.of(location));
    }

    public Image(String name, LocalDate date, List<String> tags, String location) {
        this(name, date, tags, Path.of(location));
    }

    public Image addTag(String tag) {
        if (tag == null || tag.isBlank() || tags.contains(tag)) {
            return this;
        }

        List<String> newTags = new ArrayList<>(tags);
        newTags.add(tag);
        return new Image(name, date, newTags, location);
    }

}