package game.entity;
/**
 * Player is a class that represent a user player
 * @author Sanggyun Lee
 */
public class Player {
    private int row;
    private int col;
    private double hp;
    private double attack;

    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
        this.hp = 100.0;
        this.attack = 10.0;
    }

    public int getRow() { return this.row;}
    public int getCol() { return this.col;}
    public double getHp() { return this.hp;}
    public double getAttack() { return this.attack;}
    
    //this is to update the player's position after a move
    public void setPosition(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }

    public void setHp(double damageTaken) { this.hp -= damageTaken; }

    public void getAttack(double additionalAttack) { this.attack += additionalAttack;}

    public boolean isAlive() { return this.hp > 0.0;}

    }

    
