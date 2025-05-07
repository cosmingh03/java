package com.lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();
    private final List<Player> players = new ArrayList<>();
    private final AtomicBoolean gameActive = new AtomicBoolean(true);

    private int currentRound = 1;
    private int currentPlayerIndex = 0;
    private int playersFinishedInCurrentRound = 0;

    private TimeKeeper timeKeeper;

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public boolean isGameActive() {
        return gameActive.get();
    }

    public void endGame() {
        gameActive.set(false);
        synchronized (this) {
            notifyAll();
        }
    }

    public void addPlayer(String name) {
        players.add(new Player(name, this));
    }

    public synchronized boolean isPlayerTurn(Player player) {
        return players.get(currentPlayerIndex).getName().equals(player.getName());
    }

    public synchronized void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        notifyAll();
    }

    public synchronized void playerFinishedTurn() {
        playersFinishedInCurrentRound++;

        if (playersFinishedInCurrentRound == players.size()) {
            playersFinishedInCurrentRound = 0;
            currentRound++;
            currentPlayerIndex = 0;

            if (bag.isEmpty()) {
                System.out.println("\nempty bag at round: " + (currentRound - 1));
                endGame();
                return;
            }

            System.out.println("\n-round " + currentRound + " -");
            notifyAll();
        } else {
            nextPlayer();
        }
    }

    public void play() {
        System.out.println("word game started!");
        System.out.println(players.size() + " players");
        System.out.println("\n-round 1-");

        timeKeeper = new TimeKeeper(this, 60);
        timeKeeper.start();

        List<Thread> threads = new ArrayList<>();
        for (Player player : players) {
            Thread t = new Thread(player);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        timeKeeper.stopTimer();

        System.out.println("\n-game ended-");
        Player winner = null;
        int maxScore = -1;

        System.out.println("scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore() + " ppoints");
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
                winner = player;
            }
        }

        if (winner != null) {
            System.out.println("\nwinner: " + winner.getName() + " : " + maxScore + " points!");
        } else {
            System.out.println("\nno winner!");
        }

    }

    public void loadDictionary(String filePath) {
        dictionary.loadFromFile(filePath);
    }
}