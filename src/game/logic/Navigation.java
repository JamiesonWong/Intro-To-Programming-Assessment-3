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
       int row = player.getRow();
       int col = player.getCol();

       return switch (direction) {
        case "north" -> row > 0;
        case "south" -> row < mapSize - 1; // mapsize - 1 because index start from 0
        case "east" -> col < mapSize - 1;
        case "west" -> col > 0;
        default -> false;

       };
    }


    public static int[] getNewPosition(Player player, String direction) {
        int row = player.getRow();
        int col = player.getCol();
        

        return switch (direction) {
         case "north" -> new int[] {row -1, col}; // even if we going 'up' but actually we are moving to -1 row.
         case "south" -> new int[] {row +1, col}; // same as above row + 1 index. when we go the row below.
         case "east" -> new int[] {row, col +1 };
         case "west" -> new int[] {row, col -1 };
         default -> new int[] {row, col};
        };
    }
}
