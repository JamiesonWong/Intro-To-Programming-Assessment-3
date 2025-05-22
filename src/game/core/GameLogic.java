package game.core;

import game.entity.Enemy;
import game.entity.Player;
import game.logic.Combat;
import game.logic.Navigation;
import game.world.Location;
import java.util.Scanner;

/**
 * GameLogic.java's purpose is to manage the game flow, and to manage the
 * backbone logic of the game such as user input, game loop, game start/end
 * it essentially controls the states of the games.
 * 
 * @author Sanggyun Lee
 */
public class GameLogic {

    private Location[][] map;
    private Player player;
    private final int MAP_SIZE = 5; // map size not going to change

    public void start() {
        map = generateMap();
        player = new Player(2,2);
        
        //need to get user input
        Scanner scanner = new Scanner(System.in); 



        while (player.isAlive()) {
            System.out.println("Command (n/e/s/w/q/map/hp/i): ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("q")) {
                System.out.println("Thank you for playing!");
                break;
            } else if (userInput.equals("map")) {
                printMap();

            } else if (userInput.equals("hp")) {
                System.out.println(player.getHp());
            } else if (userInput.equals("i")) {
                printInventory();
            } else {
                // Here is where the navigaion logic goes.
                if (!Navigation.isValidMove(player, userInput, MAP_SIZE)) {
                    System.out.println("You can't move out of the bounds");
                    continue; //so we can keep run the while loop
                }

                int[] nextIntendedPosition = Navigation.getNewPosition(player, userInput);
                // int[row,col] so we have to type 0,1
                int newRow = nextIntendedPosition[0]; //extract the data
                int newCol = nextIntendedPosition[1];
                //this is to check your location logic
                Location newLocation = map[newRow][newCol];
                if (newLocation.isImpossible()) {
                    System.out.println("This area not exist. Try another direction.");
                    continue; // we have to keep run to get correct input from the user
                }

                if (player.getHp() < 0.0 ) {
                    System.out.println("You died. Game over.");
                    break; // If player died, end game
                }
                
                //this is where the player moves to the new location
                player.setPosition(newRow, newCol);
                
                System.out.println("You have entered: " + newLocation.getName());

                //check the itmes we obtain
                giveItemIfAvailable(newLocation.getName().toLowerCase());
                
                //we need to check that the new location has enermy or not
                //if there is enemy we have to give the information about the enemy and combat
                if (newLocation.hasEnermy()) {
                    Enemy enemy = newLocation.getEnermy();
                    //engage with the enemy
                    boolean survived = Combat.engage(player, enemy, scanner);
                    //when we are combatting with enemy, we survive or die
                    if (!survived) {
                        System.out.println("you died, game over");
                        break;
                    }

                    newLocation.clearEnermy();
                    

                }
            }           
            
        }
        scanner.close();
    }

    //generate a map of 5*5
    private Location[][] generateMap() {
        String[][] rawMap = {
        {"x", "x", "cristal cave", "broken bridge", "temple of light"},
        {"x", "x", "howling cliff", "x", "x"},
        {"x", "desert", "forrest", "misty swamp", "x"},
        {"beach", "lost village", "x", "everdark woods", "x"},
        {"x", "x", "x", "frozen lake", "mystic library"}
    };

    Location[][] map = new Location[MAP_SIZE][MAP_SIZE];

    for (int r = 0; r < MAP_SIZE; r++) {
        for (int c = 0; c < MAP_SIZE; c++) {
            String name = rawMap[r][c];
            if (name.equals("x")) {
                map[r][c] = new Location("This area does not exist", true);
            } else {
                map[r][c] = new Location(name, false);
            }
        }
    }

        return map;
    }


    private void printMap() {
        System.out.println("Map:");

    for (int r = 0; r < MAP_SIZE; r++) {
        for (int c = 0; c < MAP_SIZE; c++) {
            if (player.getRow() == r && player.getCol() == c) {
                System.out.print("P "); // player location
            } else if (map[r][c].isImpossible()) {
                System.out.print("X "); // not exist
            } else {
                System.out.print(". "); // exist
            }
        }
        System.out.println(); 
    }

    }
    private void printInventory() {
        System.out.println("Inventory: ");
        if (player.getInventory().isEmpty()) {
        System.out.println("  (empty)");
        } else {
        for (String item : player.getInventory()) {
            System.out.println("  - " + item);
        }
    }
    }

    //get items depend on the location ***we will change the compass item once we set the NPC system.
    private void giveItemIfAvailable(String locationName) {
    switch (locationName) {
        case "forrest":
            if (!player.getInventory().contains("compass")) {
                player.addItem("compass");
                System.out.println("You found a compass!");
            }
            if (!player.getInventory().contains("sword")) {
                player.addItem("sword");
                System.out.println("You found a sword!");
            }
            break;
        case "beach":
            if (!player.getInventory().contains("key")) {
                player.addItem("key");
                System.out.println("You found a key!");
            }
            break;
        case "frozen lake":
            if (!player.getInventory().contains("axe")) {
                player.addItem("axe");
                System.out.println("You found an axe!");
            }
            break;
        case "mystic library":
            if (!player.getInventory().contains("branch")) {
                player.addItem("branch");
                System.out.println("You found a mysterious branch!");
            }
            break;
    }
}
}
