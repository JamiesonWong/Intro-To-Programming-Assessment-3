package game.core;

import game.entity.Enemy;
import game.entity.Player;
import game.entity.NPC;
import game.logic.Combat;
import game.logic.Navigation;
import game.world.Location;
import java.util.Scanner;

/**
 * GameLogic.java manages the game flow, user input, game loop, and game states.
 * 
 * @author Sanggyun Lee
 */
public class GameLogic {
    private GameMap gameMap;
    private Player player;
    private final int MAP_SIZE = 5;
    private boolean isRunning;

    public GameLogic() {
        this.gameMap = new GameMap(MAP_SIZE);
        this.player = new Player(2, 2);
        this.isRunning = true;
        setupItemsAndNPCs();
    }

    private void setupItemsAndNPCs() {
        // Forest items and NPC
        Location forest = gameMap.getLocation(2, 2);
        forest.addItem(new Item("Sword", "A sharp blade for fighting enemies.", true));
        forest.addItem(new Item("Compass", "Shows your location on the map.", false));
        forest.setNpc(new NPC("Wise Old Man", "Take the sword and find your way."));

        // Beach items
        Location beach = gameMap.getLocation(3, 0);
        beach.addItem(new Item("Shovel", "A sturdy tool perfect for digging.", false));
        beach.setBuriedItem(new Item("Key", "A mysterious key that might unlock something important.", true));

        // Frozen Lake items
        gameMap.getLocation(4, 3).addItem(new Item("Axe", "Stuck in a tree, but you managed to pry it out.", false));

        // Mystic Library items and NPC
        Location library = gameMap.getLocation(4, 4);
        library.addItem(new Item("Branch", "Could help bridge a gap.", false));
        library.setNpc(new NPC("Shadow Beast", "Grrrrr..."));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Text Adventure!");
        System.out.println("You begin your journey in the Forest.");

        while (isRunning && player.isAlive()) {
            Location currentLocation = gameMap.getLocation(player.getRow(), player.getCol());
            currentLocation.describe();

            System.out.println("\nCommand (n/e/s/w/q/map/hp/i/f): ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("q")) {
                System.out.println("Thank you for playing!");
                break;
            } else if (userInput.equals("map")) {
                boolean hasCompass = player.getInventory().hasItem("Compass");
                gameMap.printMap(player.getRow(), player.getCol());
            } else if (userInput.equals("hp")) {
                System.out.println(player.getHp());
            } else if (userInput.equals("i")) {
                printInventory();
            } else if (userInput.equals("f")) {
                handleInteraction(currentLocation);
            } else {
                if (!Navigation.isValidMove(player, userInput, MAP_SIZE)) {
                    System.out.println("You can't move out of the bounds");
                    continue;
                }

                int[] nextIntendedPosition = Navigation.getNewPosition(player, userInput);
                int newRow = nextIntendedPosition[0];
                int newCol = nextIntendedPosition[1];
                Location newLocation = gameMap.getLocation(newRow, newCol);
                
                if (newLocation == null || newLocation.isImpossible()) {
                    System.out.println("This area does not exist. Try another direction.");
                    continue;
                }

                player.setPosition(newRow, newCol);
                System.out.println("You have entered: " + newLocation.getName());

                // Automatic item pickup for basic items
                giveBasicItems(newLocation);

                if (newLocation.hasEnemy()) {
                    Enemy enemy = newLocation.getEnemy();
                    boolean survived = Combat.engage(player, enemy, scanner);
                    if (!survived) {
                        System.out.println("You died, game over");
                        isRunning = false;
                        break;
                    }
                    newLocation.clearEnemy();
                }
            }

            checkVictoryConditions();
        }
        scanner.close();
    }

    private void handleInteraction(Location location) {
        // Special interactions that require 'f' key
        // Dig if shovel and buried item present
        if (location.hasBuriedItem()) {
            if (player.getInventory().hasItem("Shovel")) {
                System.out.println("You dig into the ground...");
                Item buried = location.getBuriedItem();
                System.out.println("You found a " + buried.getName() + "!");
                player.addItem(buried);
                location.setBuriedItem(null);
            } else {
                System.out.println("You sense something is buried here... maybe a tool would help.");
            }
        }

        // Handle NPC interaction
        if (location.getNpc() != null && !location.getNpc().isDefeated()) {
            NPC npc = location.getNpc();
            npc.talk();

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

    private void giveBasicItems(Location location) {
        // Automatic pickup for visible items (except those that should require special interaction)
        if (!location.getItems().isEmpty()) {
            for (Item item : location.getItems()) {
                // Skip items that should require special interaction
                if (item.getName().equals("Branch") && location.getName().equalsIgnoreCase("mystic library")) {
                    continue; // Branch needs interaction with Shadow Beast
                }
                player.addItem(item);
                System.out.println("You found " + item.getName() + "!");
            }
            location.getItems().clear();
        }
    }

    private void checkVictoryConditions() {
        Location currentLocation = gameMap.getLocation(player.getRow(), player.getCol());
        if (currentLocation.getName().equalsIgnoreCase("temple of light")) {
            if (player.getInventory().hasItem("Key")) {
                System.out.println("You used the key to unlock the Temple of Light and won the game!");
                isRunning = false;
            } else {
                System.out.println("The gate to the temple is locked. You need a key.");
            }
        }
    }

    private void printInventory() {
        System.out.println("Inventory: ");
        if (player.getInventory().getItems().isEmpty()) {
            System.out.println("  (empty)");
        } else {
            for (String item : player.getInventory().getItems()) {
                System.out.println("  - " + item);
            }
        }
    }
}