package game.core;

import game.world.Location;

import java.util.Scanner;

import game.entity.Player;

/**
 * GameLogic.java's purpose is to manage the game flow, and to manage the
 * backbone logic of the game such as user input, game loop, game start/end
 * it essentially controls the states of the games.
 * 
 * @seanlee1991
 */
public class GameLogic {
    private Location[][] map;
    private Player player;

    public void start() {
        map = generateMap();
        player = new Player(2,2);
        
        //need to get user input
        Scanner scanner = new Scanner(System.in); 



        while (true) {
            System.out.println("Command (n/e/s/w/q): ");
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
                //here is where the navigation logic goes
                
            }
        }
    }
}
