public class Player {
    private int x, y; // Player position on the map
    private Inventory inventory;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.inventory = new Inventory();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void move(String direction, GameMap map) {
        int newX = x, newY = y;
        switch (direction.toLowerCase()) {
            case "w": newY++; break;
            case "s": newY--; break;
            case "a": newX--; break;
            case "d": newX++; break;
            default:
                System.out.println("Invalid direction. Use W/A/S/D.");
                return;
        }

        Location loc = map.getLocation(newX, newY);
        if (loc != null && loc.isPassable()) {
            x = newX;
            y = newY;
            System.out.println("You moved to: " + loc.getName());
        } else {
            System.out.println("You can't move in that direction.");
        }
    }
}
