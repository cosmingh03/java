package com.lab7;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final List<Player> players = new ArrayList<>();

    private int currentRound = 1;
    private int playersFinishedInCurrentRound = 0;

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void addPlayer(String name) {
        players.add(new Player(name, this));
    }

    public synchronized void playerFinishedTurn() {
        playersFinishedInCurrentRound++;

        if (playersFinishedInCurrentRound == players.size()) {
            playersFinishedInCurrentRound = 0;
            currentRound++;
            if (currentRound <= 3) {
                System.out.println("\nround " + currentRound);
            }

            notifyAll();
        }
    }

    public void play() {
        System.out.println("game started");
        System.out.println("\nround 1");

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

        System.out.println("\ngame ended");
        Player winner = null;
        int maxScore = -1;

        for (Player player : players) {
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
                winner = player;
            }
        }

        if (winner != null) {
            System.out.println("winner: " + winner.getName() + " -" + winner.getScore() + " p");
        }
    }
}