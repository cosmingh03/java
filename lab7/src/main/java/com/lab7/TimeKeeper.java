package com.lab7;

import java.util.concurrent.atomic.AtomicBoolean;

public class TimeKeeper extends Thread {
    private final Game game;
    private final long timeLimit;
    private final AtomicBoolean running = new AtomicBoolean(true);
    private long startTime;

    public TimeKeeper(Game game, long timeLimitSeconds) {
        this.game = game;
        this.timeLimit = timeLimitSeconds * 1000;
        this.setDaemon(true);
        System.out.println("TimeKeeper with limit " + timeLimitSeconds + " seconds");
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();

        while (running.get()) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;

            if (elapsedTime / 1000 % 5 == 0 && elapsedTime > 1000) {
                System.out.println((elapsedTime / 1000) + " seconds passed");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (elapsedTime > timeLimit) {
                System.out.println("\ntime expired!");
                game.endGame();
                break;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopTimer() {
        running.set(false);
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("\ngame stoped after " + (totalTime / 1000.0) + " seconds");
    }
}