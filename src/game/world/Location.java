package game.world;

import game.entity.Enemy;
import game.entity.NPC;
import game.core.Item;
import java.util.ArrayList;
import java.util.List;

/**
 * Location.java is the blueprint for each of the 5*5 map tiles
 * @author Sanggyun Lee
 */

public class Location {
    private String name;
    private String description;
    private boolean impossible;
    private Enemy enemy;
    private NPC npc;
    private List<Item> items;
    private Item buriedItem;

    public Location(String name, String description, boolean impossible) {
        this.name = name;
        this.description = description;
        this.impossible = impossible;
        this.enemy = null;
        this.npc = null;
        this.items = new ArrayList<>();
        this.buriedItem = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isImpossible() {
        return impossible;
    }

    public void setImpossible(boolean impossible) {
        this.impossible = impossible;
    }

    public boolean hasEnemy() {
        return enemy != null;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void clearEnemy() {
        this.enemy = null;
    }

    public boolean isPassable() {
        return !impossible;
    }

    public void describe() {
        System.out.println(name + ": " + description);
        if (!items.isEmpty()) {
            System.out.println("You see:");
            for (Item item : items) {
                System.out.println("- " + item.getName());
            }
        }
    }

    // Item related methods
    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setBuriedItem(Item item) {
        this.buriedItem = item;
    }

    public Item getBuriedItem() {
        return buriedItem;
    }

    public boolean hasBuriedItem() {
        return buriedItem != null;
    }

    // NPC related methods
    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public NPC getNpc() {
        return npc;
    }
}