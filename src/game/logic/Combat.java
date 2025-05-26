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
        System.out.println("You encountered a " + enemy.getName() + "!");
        
        while (enemy.isAlive() && player.isAlive()) {
            System.out.println("\nYour HP: " + player.getHp());
            System.out.println("Enemy HP: " + enemy.getHp());
            System.out.println("\nWhat do you want to do? (attack/run): ");
            
            String action = scanner.nextLine().toLowerCase();
            
            if (action.equals("run")) {
                if (Math.random() < 0.5) {
                    System.out.println("You successfully ran away!");
                    return true;
                } else {
                    System.out.println("Couldn't escape!");
                }
            } else if (action.equals("attack")) {
                // Player attacks enemy
                double playerDamage = player.getAttack();
                enemy.takeDamage(playerDamage);
                System.out.println("You dealt " + playerDamage + " damage!");
                
                if (!enemy.isAlive()) {
                    System.out.println("You defeated the " + enemy.getName() + "!");
                    return true;
                }
                
                // Enemy attacks player
                double enemyDamage = enemy.getAttack();
                player.setHp(enemyDamage);
                System.out.println("Enemy dealt " + enemyDamage + " damage!");
            }
        }
        
        return player.isAlive();
    }
}
