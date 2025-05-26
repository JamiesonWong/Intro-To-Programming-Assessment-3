package game.core;

public class Item {
    private String name;
    private String description;
    private boolean isKeyItem;

    public Item(String name, String description, boolean isKeyItem) {
        this.name = name;
        this.description = description;
        this.isKeyItem = isKeyItem;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isKeyItem() {
        return isKeyItem;
    }
} 