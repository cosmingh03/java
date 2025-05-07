package com.lab7;

public class App {
    public static void main(String args[]) {
        Game game = new Game();

        game.addPlayer("Player1");
        game.addPlayer("Player2");
        game.addPlayer("Player3");
        game.loadDictionary("src\\main\\java\\com\\lab7\\dictionary.txt");

        game.play();
    }
}
