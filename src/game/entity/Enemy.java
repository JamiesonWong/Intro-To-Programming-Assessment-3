package game.entity;
/**
 * @author Sanggyun Lee
 */
public class Enemy {

    private String name;
    private double hp;
    private double attack;
    private boolean alive;

    public Enemy (String name, double hp, double attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.alive = true;
    }

    public String getName() { return this.name;}
    public double getHp() { return this.hp;}
    public double getAttack() { return this.attack;}

    public boolean isAlive() { return this.alive; }
   
    public void takeDamage(double damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            this.alive = false;
        }
    }
}
