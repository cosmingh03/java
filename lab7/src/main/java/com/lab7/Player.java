package com.lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Runnable {
    private final String name;
    private final Game game;
    private int score = 0;
    private final List<Tile> tiles = new ArrayList<>();
    private final Random random = new Random();

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    private void createAndSubmitWord() {
        if (tiles.isEmpty()) {
            List<Tile> newTiles = game.getBag().extractTiles(7);
            tiles.addAll(newTiles);
            System.out.println(name + " extracted " + newTiles.size() + " tiles");
        }

        String word = game.getDictionary().findValidWord(tiles);

        if (word != null && !word.isEmpty()) {
            int wordPoints = 0;

            for (char c : word.toCharArray()) {
                for (int i = 0; i < tiles.size(); i++) {
                    Tile tile = tiles.get(i);
                    if (tile.getLetter() == c) {
                        wordPoints += tile.getPoints();
                        tiles.remove(i);
                        break;
                    }
                }
            }
            score += wordPoints;
            game.getBoard().addWord(this, word, wordPoints);

            int k = word.length();
            if (!game.getBag().isEmpty()) {
                List<Tile> newTiles = game.getBag().extractTiles(k);
                tiles.addAll(newTiles);
                System.out.println(name + " extracted " + newTiles.size() + " new tiles");
            }
        } else {
            System.out.println(name + " can t form a word, turn passed");

            tiles.clear();

            if (!game.getBag().isEmpty()) {
                List<Tile> newTiles = game.getBag().extractTiles(7);
                tiles.addAll(newTiles);
                System.out.println(name + " extracted " + newTiles.size() + " new tiles");
            }
        }
    }

    @Override
    public void run() {
        while (game.isGameActive() && (!game.getBag().isEmpty() || !tiles.isEmpty())) {
            synchronized (game) {
                while (game.isGameActive() && !game.isPlayerTurn(this)) {
                    try {
                        game.wait(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (!game.isGameActive()) {
                    break;
                }

                createAndSubmitWord();

                if (game.getBag().isEmpty() && tiles.isEmpty()) {
                    break;
                }

                try {
                    Thread.sleep(random.nextInt(300) + 200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (game.isGameActive()) {
                    game.playerFinishedTurn();
                } else {
                    break;
                }
            }
        }

        System.out.println("\n" + name + " finished with " + score + " points");
    }
}