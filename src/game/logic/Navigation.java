package game.logic;

import game.entity.Player;

/**
 * Navigation.java handles the movement of logic such as checking boundaries.
 * is a move valid? calculating new position etc.
 * it's basiacally traffic controller. it does not store the map itself
 * it takes the current state of the game like player position, direction, map size
 * it gives the new position of the player basically the movent valid or not valid
 * 
 * @author seanlee1991
 */

public class Navigation {
    
    public static boolean isValidMove(Player player, String direction, int mapSize) {
        int newRow = player.getRow();
        int newCol = player.getCol();
        
        switch (direction) {
            case "w": newRow--; break;
            case "s": newRow++; break;
            case "a": newCol--; break;
            case "d": newCol++; break;
            default: return false;
        }
        
        return newRow >= 0 && newRow < mapSize && newCol >= 0 && newCol < mapSize;
    }

    public static int[] getNewPosition(Player player, String direction) {
        int newRow = player.getRow();
        int newCol = player.getCol();
        
        switch (direction) {
            case "w": newRow--; break;
            case "s": newRow++; break;
            case "a": newCol--; break;
            case "d": newCol++; break;
        }
        
        return new int[]{newRow, newCol};
    }
}
