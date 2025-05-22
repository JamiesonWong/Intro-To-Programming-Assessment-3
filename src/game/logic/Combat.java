package game.logic;
/**
 * 
 * @author Sanggyun Lee
 */
import game.entity.Enemy;
import game.entity.Player;
import java.util.Scanner;


public class Combat {

    public static boolean engage(Player player, Enemy enemy, Scanner scanner) {
        System.out.printf("A wild %s has appeared! (%.0f HP)\n", enemy.getName(), enemy.getHp());
        
        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("Do you want to 'roll' to attack or 'flee' to run?");
            String action = scanner.nextLine().strip().toLowerCase();

            if(action.equals("roll")) {
                enemy.setHp(player.getAttack());
                System.out.printf("You hit the %s! its HP is now %.0f.\n",enemy.getName(),enemy.getHp());

                if(enemy.isAlive()) {
                    player.setHp(enemy.getAttact());
                    System.out.printf("The %s hit back! your HP is now %.0f.\n",enemy.getName(),player.getHp());
                } else {
                    System.out.println("You win!");
                }                
            } else if (action.equals("flee")) {
                System.out.println("You run away");
                return false;               
            } else {
                System.out.println("please type 'roll' or 'flee' only"); // If user type wrong command
            }
        } 
         return player.isAlive(); //once we win, we have to return. instead of true on 'error5'
       
        
    }
}
