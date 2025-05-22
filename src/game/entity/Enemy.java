package game.entity;
/**
 * @author Sanggyun Lee
 */
public class Enemy {

    private String name;
    private double hp;
    private double attack;

    public Enemy (String name, double hp, double attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    public String getName() { return this.name;}
    public double getHp() { return this.hp;}
    public double getAttact() { return this.attack;}

    public void setHp(double damageTaken) { this.hp -= damageTaken; }
    public boolean isAlive() { return this.hp > 0.0;}
   
}
