import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Game {
    private GameMap map;
    private Player player;
    private boolean isRunning;

    public Game() {
        map = new GameMap();
        player = new Player(2, 2); // Start at Forest [3,3]
        isRunning = true;

        setupItemsAndNPCs();
    }

    private void setupItemsAndNPCs() {
        // Compass, Sword, NPC at Forest
        Location forest = map.getLocation(2, 2);
        forest.addItem(new Item("Sword", "A sharp blade.", true));
        forest.addItem(new Item("Compass", "Shows your location on the map.", true));
        forest.setNpc(new NPC("Wise Old Man", "Take the sword and find your way."));
        

        

        // Key at Beach (buried)
        // Key and Shovel at Beach
        Location beach = map.getLocation(0, 3);
        beach.addItem(new Item("Shovel", "A sturdy tool perfect for digging.", true));
        beach.setBuriedItem(new Item("Key", "It's been dug up from the sand.", true));

        // Axe at Frozen Lake
        map.getLocation(3, 4).addItem(new Item("Axe", "Stuck in a tree, but you managed to pry it out.", true));

        // Branch at Mystic Library (guarded)
        map.getLocation(4, 4).addItem(new Item("Branch", "Could help bridge a gap.", true));
        map.getLocation(4, 4).setNpc(new NPC("Shadow Beast", "Grrrrr...")); // Enemy

        // Additional logic can include setting condition flags on interaction
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Text Adventure!");
        System.out.println("You begin your journey in the Forest.");

        while (isRunning) {
            Location currentLocation = map.getLocation(player.getX(), player.getY());
            System.out.println("\n" + currentLocation.getDescription());

            System.out.print("\nEnter command (WASD to move, E = Inventory, F = Interact, M = Map, Q = Quit): ");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "w": case "a": case "s": case "d":
                    player.move(input, map);
                    break;
                case "e":
                    player.getInventory().showInventory();
                    break;
                case "f":
                    handleInteraction(currentLocation);
                    break;
                case "m":
                    boolean hasCompass = player.getInventory().hasItem("Compass");
                    map.printMap(player.getX(), player.getY(), hasCompass);

                    break;
                case "q":
                    isRunning = false;
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Unknown command.");
            }

            checkVictoryConditions();
        }

        scanner.close();
    }

    private void handleInteraction(Location location) {
        // Pick up visible items
if (!location.getItems().isEmpty()) {
    for (Item item : location.getItems()) {
        player.getInventory().addItem(item);
    }
    location.getItems().clear();
}

// Dig if shovel and buried item present
if (location.hasBuriedItem()) {
    if (player.getInventory().hasItem("Shovel")) {
        System.out.println("You dig into the ground...");
        Item buried = location.getBuriedItem();
        
        System.out.println("You found a " + buried.getName() + "!");
        player.getInventory().addItem(buried);
        location.setBuriedItem(null); // Remove buried item
    } else {

        System.out.println("You sense something is buried here... maybe a tool would help.");
    }
}

        if (location.getNpc() != null && !location.getNpc().isDefeated()) {
            NPC npc = location.getNpc();
            System.out.println(npc.getDialogue());

            if (npc.getName().equals("Shadow Beast")) {
                if (player.getInventory().hasItem("Sword")) {
                    System.out.println("You defeated the Shadow Beast!");
                    npc.defeat();
                } else {
                    System.out.println("You were defeated. Game over.");
                    isRunning = false;
                }
            }
        }
    }

    private void checkVictoryConditions() {
        // If at Temple of Light and has the key
        if (player.getX() == 4 && player.getY() == 0) {
            if (player.getInventory().hasItem("Key")) {
                System.out.println("You used the key to unlock the Temple of Light and won the game!");
                isRunning = false;
            } else {
                System.out.println("The gate to the temple is locked. You need a key.");
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
