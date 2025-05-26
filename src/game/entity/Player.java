package game.entity;

import game.core.Inventory;
import game.core.Item;

/**
 * Player is a class that represent a user player
 * @author Sanggyun Lee
 */
public class Player {
    private int row, col; // Player position on the map
    private double hp;
    private double attack;
    private Inventory inventory;

    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
        this.hp = 100.0;
        this.attack = 10.0;
        this.inventory = new Inventory();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getHp() { 
        return this.hp;
    }
    
    public double getAttack() { 
        return this.attack;
    }
    
    public void setPosition(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }

    public void setHp(double damageTaken) { 
        this.hp -= damageTaken; 
    }

    public boolean isAlive() { 
        return this.hp > 0.0;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItem(Item item) { 
        inventory.addItem(item); 
    }
}

    
