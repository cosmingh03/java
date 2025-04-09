package com.lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {
    private final List<Tile> tiles = new ArrayList<>();
    private final Random random = new Random();

    public Bag() {
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < 5; i++) {
                int points = random.nextInt(10) + 1;
                tiles.add(new Tile(c, points));
            }
        }
    }

    public synchronized List<Tile> extractTiles(int count) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < count && !tiles.isEmpty(); i++) {
            extracted.add(tiles.remove(random.nextInt(tiles.size())));
        }
        return extracted;
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}
