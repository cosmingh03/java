package com.lab7;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private final Game game;
    private int score = 0;
    private final List<Tile> tiles = new ArrayList<>();

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

    @Override
    public void run() {
        for (int round = 1; round <= 3; round++) {
            synchronized (game) {
                while (game.getCurrentRound() != round) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (tiles.isEmpty()) {
                    tiles.addAll(game.getBag().extractTiles(7));
                }

                StringBuilder word = new StringBuilder();
                int wordPoints = 0;

                List<Tile> usedTiles = new ArrayList<>();
                for (int i = 0; i < 4 && i < tiles.size(); i++) {
                    Tile tile = tiles.get(i);
                    word.append(tile.getLetter());
                    wordPoints += tile.getPoints();
                    usedTiles.add(tile);
                }

                tiles.removeAll(usedTiles);

                score += wordPoints;
                game.getBoard().addWord(this, word.toString(), wordPoints);

                game.playerFinishedTurn();
            }
        }

        System.out.println(name + " - " + score + " points");
        System.out.println(name + " finished");
    }
}