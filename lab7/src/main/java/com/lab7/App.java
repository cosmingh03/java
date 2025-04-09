package com.lab7;

public class App {
    public static void main(String args[]) {
        Game game = new Game();
        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.addPlayer("Player 3");
        game.play();
    }
}
