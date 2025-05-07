package com.lab7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
    private final Set<String> words = new HashSet<>();

    public Dictionary() {
        loadDefaultWords();
    }

    public void loadFromFile(String filePath) {
        try {
            Files.lines(Paths.get(filePath))
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .forEach(words::add);

            System.out.println(words.size() + " words added");
        } catch (IOException e) {
            System.err.println("error at loading from file" + e.getMessage());
            loadDefaultWords();
        }
    }

    private void loadDefaultWords() {
        String[] defaultWords = {
                "the", "be", "to", "of", "and", "a", "in", "that", "have", "it",
                "for", "not", "on", "with", "he", "as", "you", "do", "at", "this",
                "but", "his", "by", "from", "they", "we", "say", "her", "she", "or",
                "an", "will", "my", "one", "all", "would", "there", "their", "what", "so",
                "up", "out", "if", "about", "who", "get", "which", "go", "me", "when",
                "make", "can", "like", "time", "no", "just", "him", "know", "take", "people",
                "word", "game", "play", "win", "lose", "point", "score", "tile", "turn", "bag",
                "cat", "dog", "bat", "hat", "rat", "mat", "sat", "fat", "man", "ran",
                "cab", "dab", "lab", "tab", "fab", "nab", "jab", "car", "jar", "bar"
        };

        words.addAll(Arrays.asList(defaultWords));

        System.out.println("default: " + words.size() + " words");
    }

    public boolean isWord(String str) {
        return words.contains(str.toLowerCase());
    }

    public String findValidWord(List<Tile> tiles) {
        char[] characters = new char[tiles.size()];
        for (int i = 0; i < tiles.size(); i++) {
            characters[i] = tiles.get(i).getLetter();
        }

        for (int len = Math.min(7, tiles.size()); len >= 2; len--) {
            String result = findWord(characters, len, "", new boolean[characters.length]);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    private String findWord(char[] chars, int length, String current, boolean[] used) {
        if (current.length() == length) {
            if (isWord(current)) {
                return current;
            }
            return null;
        }

        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {
                used[i] = true;
                String result = findWord(chars, length, current + chars[i], used);
                if (result != null) {
                    return result;
                }
                used[i] = false;
            }
        }

        return null;
    }
}