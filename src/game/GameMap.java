public class GameMap {
    private Location[][] grid;

    public GameMap() {
        grid = new Location[5][5]; // 0-indexed, [x][y]

        // Fill impassable by default
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                grid[x][y] = new Location("Blocked", "You can't go here.", false);
            }
        }

        // Define actual locations
        grid[2][2] = new Location("Forest", "A dense forest with a whispering wind.", true); // 3,3
        grid[1][2] = new Location("Desert", "Hot and dry sands stretch endlessly.", true); // 2,3
        grid[1][3] = new Location("Lost Village", "Ruins of an old settlement.", true); // 2,4
        grid[0][3] = new Location("The Beach", "You hear waves crashing. Something is buried here.", true); // 1,4
        grid[3][2] = new Location("Misty Swamp", "Thick fog and mud surrounds you.", true); // 4,3
        grid[3][3] = new Location("Everdark Woods", "The woods are unnaturally quiet.", true); // 4,4
        grid[3][4] = new Location("Frozen Lake", "An axe is frozen in a tree trunk.", true); // 4,5
        grid[4][4] = new Location("Mystic Library", "A dark figure guards a glowing branch.", true); // 5,5
        grid[2][1] = new Location("Howling Cliff", "Wind howls over sharp rocks.", true); // 3,2
        grid[2][0] = new Location("Crystal Cave", "Crystals light up the walls.", true); // 3,1
        grid[3][1] = new Location("Broken Bridge", "The bridge is out. You may need to fix it.", true); // 4,2
        grid[4][0] = new Location("Temple of Light", "A glowing gate blocks your path.", true); // 5,1
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < 5 && y >= 0 && y < 5) {
            return grid[x][y];
        }
        return null;
    }

    public void printMap(int playerX, int playerY, boolean revealDetails){
        System.out.println("=== Game Map ===");
        for (int y = 4; y >= 0; y--) {
            for (int x = 0; x < 5; x++) {
                if (x == playerX && y == playerY) {
                    if (playerX == x && playerY == y) {
    System.out.print("[P]");
    } else if (revealDetails) {
        if (grid[y][x] != null && grid[y][x].isPassable()) {
            System.out.print("[L]"); // Known location
        } else {
            System.out.print("[X]"); // Impassable
        }
    } else {
        System.out.print("[ ]"); // Hidden info
}

                } else if (grid[x][y].isPassable()) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[X]");
                }
            }
            System.out.println();
        }
    }
}
