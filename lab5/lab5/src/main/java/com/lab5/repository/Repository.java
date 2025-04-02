package com.lab5.repository;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lab5.Errors.ImageNotFoundException;
import com.lab5.Image.Image;

public class Repository {
    private final List<Image> images;

    public Repository() {
        this.images = new ArrayList<>();
    }

    public boolean add(Image image) {
        if (image == null) {
            return false;
        }

        if (images.stream().anyMatch(img -> img.name().equals(image.name()))) {
            return false;
        }

        return images.add(image);
    }

    public Image findImageByName(String name) {
        if (name == null || name.isBlank()) {
            throw new ImageNotFoundException("name is null or blank");
        }

        return images.stream()
                .filter(img -> img.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new ImageNotFoundException("image" + name + " not found"));
    }

    public List<Image> getAll() {
        return Collections.unmodifiableList(images);
    }

    public int getSize() {
        return images.size();
    }

    public boolean remove(String name) {
        return images.removeIf(img -> img.name().equals(name));
    }

    public void displayImage(Image image) throws IOException {

        if (image == null) {
            throw new ImageNotFoundException("image is null");
        }

        Desktop desktop = Desktop.getDesktop();
        File file = image.location().toFile();

        if (!file.exists()) {
            throw new IOException("file not exists");
        }

        desktop.open(file);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("repo:\n");
        if (images.isEmpty()) {
            sb.append("  (is emply)\n");
        } else {
            for (Image img : images) {
                sb.append("name: ").append(img.name()).append(" location: (").append(img.location()).append(")\n");
            }
        }
        return sb.toString();
    }

    public void updateName(String name, String newName) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("New name cannot be null or blank");
        }
        if (!images.stream().anyMatch(img -> img.name().equals(name))) {
            throw new IllegalArgumentException("Image with this name does not exist");
        }

        for (Image img : images) {
            if (img.name().equals(name)) {
                images.remove(img);
                images.add(new Image(newName, img.date(), img.tags(), img.location()));
                break;
            }
        }
    }

    public void updateLocation(String name, String newLocation) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (newLocation == null || newLocation.isBlank()) {
            throw new IllegalArgumentException("New location cannot be null or blank");
        }
        if (!images.stream().anyMatch(img -> img.name().equals(name))) {
            throw new IllegalArgumentException("Image with this name does not exist");
        }

        for (Image img : images) {
            if (img.name().equals(name)) {
                images.remove(img);
                images.add(new Image(img.name(), img.date(), img.tags(), newLocation));
                break;
            }
        }
    }

    public void updateTags(String name, List<String> newTags) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (newTags == null || newTags.isEmpty()) {
            throw new IllegalArgumentException("New tags cannot be null or empty");
        }
        if (!images.stream().anyMatch(img -> img.name().equals(name))) {
            throw new IllegalArgumentException("Image with this name does not exist");
        }

        for (Image img : images) {
            if (img.name().equals(name)) {
                images.remove(img);
                images.add(new Image(img.name(), img.date(), newTags, img.location()));
                break;
            }
        }
    }

    public void updateDate(String name, String newDate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (newDate == null || newDate.isBlank()) {
            throw new IllegalArgumentException("New date cannot be null or blank");
        }
        if (!images.stream().anyMatch(img -> img.name().equals(name))) {
            throw new IllegalArgumentException("Image with this name does not exist");
        }

        for (Image img : images) {
            if (img.name().equals(name)) {
                images.remove(img);
                images.add(new Image(img.name(), img.date(), img.tags(), img.location()));
                break;
            }
        }
    }

    public void update(String name, String newName, String newLocation, List<String> newTags) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("New name cannot be null or blank");
        }
        if (newLocation == null || newLocation.isBlank()) {
            throw new IllegalArgumentException("New location cannot be null or blank");
        }
        if (newTags == null || newTags.isEmpty()) {
            throw new IllegalArgumentException("New tags cannot be null or empty");
        }
        if (!images.stream().anyMatch(img -> img.name().equals(name))) {
            throw new IllegalArgumentException("Image with this name does not exist");
        }

        for (Image img : images) {
            if (img.name().equals(name)) {
                images.remove(img);
                images.add(new Image(newName, img.date(), newTags, newLocation));
                break;
            }
        }
    }
}