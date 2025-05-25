package game.main;

import game.core.GameLogic;

/**
 * Main entry point for the game
 * @author seanlee1991
 */
public class Main {
    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.start();
    }
} 