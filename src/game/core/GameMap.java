package game.core;

import game.entity.Enemy;
import game.world.Location;

public class GameMap {
    private Location[][] locations;
    private final int size;

    public GameMap(int size) {
        this.size = size;
        this.locations = new Location[size][size];
        initializeMap();
    }

    private void initializeMap() {
        // Initialize all locations as impossible/blocked
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                locations[r][c] = new Location("Blocked Area", "This area cannot be accessed.", true);
            }
        }

        // Define actual locations with descriptions
        setLocation(0, 2, new Location("Crystal Cave", "Crystals light up the walls.", false));
        setLocation(0, 3, new Location("Broken Bridge", "The bridge is out. You may need to fix it.", false));
        setLocation(0, 4, new Location("Temple of Light", "A glowing gate blocks your path.", false));
        setLocation(1, 2, new Location("Howling Cliff", "Wind howls over sharp rocks.", false));
        setLocation(2, 1, new Location("Desert", "Hot and dry sands stretch endlessly.", false));
        setLocation(2, 2, new Location("Forest", "A dense forest with whispering winds.", false));
        setLocation(2, 3, new Location("Misty Swamp", "Thick fog and mud surrounds you.", false));
        setLocation(3, 0, new Location("Beach", "You hear waves crashing. Something is buried here.", false));
        setLocation(3, 1, new Location("Lost Village", "Ruins of an old settlement.", false));
        setLocation(3, 3, new Location("Everdark Woods", "The woods are unnaturally quiet.", false));
        setLocation(4, 3, new Location("Frozen Lake", "An axe is frozen in a tree trunk.", false));
        setLocation(4, 4, new Location("Mystic Library", "A dark figure guards a glowing branch.", false));

        // Randomly place Dark Wolf in one of the accessible locations
        // First, collect all accessible locations except starting point (Forest) 
        java.util.List<Location> accessibleLocations = new java.util.ArrayList<>();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Location loc = locations[r][c];
                // Skip if location is impossible, or if it's Forest(2,2) 
                if (!loc.isImpossible() && !(r == 2 && c == 2) ) {
                    accessibleLocations.add(loc);
                }
            }
        }

        // Randomly select a location and add the Dark Wolf
        if (!accessibleLocations.isEmpty()) {
            int randomIndex = (int)(Math.random() * accessibleLocations.size());
            Location randomLocation = accessibleLocations.get(randomIndex);
            randomLocation.setEnemy(new Enemy("Dark Wolf", 50.0, 5.0));
        }
    }

    public Location getLocation(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return locations[row][col];
        }
        return null;
    }

    public void setLocation(int row, int col, Location location) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            locations[row][col] = location;
        }
    }

    public void printMap(int playerRow, int playerCol) {
        System.out.println("=== Game Map ===");
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (r == playerRow && c == playerCol) {
                    System.out.print("[P]");
                } else if (!locations[r][c].isImpossible()) {
                    System.out.print("[ ]"); //space represents accessible locations
                } else {
                    System.out.print("[X]"); //x represents blocked locations
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Legend:");
        System.out.println("[P] - Player position");
        System.out.println("[ ] - Accessible area");
        System.out.println("[X] - Blocked area");
    }

    public int getSize() {
        return size;
    }
} 