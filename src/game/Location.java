import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private String description;
    private boolean isPassable;
    private List<Item> items;
    private NPC npc;

    public Location(String name, String description, boolean isPassable) {
        this.name = name;
        this.description = description;
        this.isPassable = isPassable;
        this.items = new ArrayList<>();
    }
    
    private Item buriedItem;

    public void setBuriedItem(Item item) {
        this.buriedItem = item;
    }

    public Item getBuriedItem() {
        return buriedItem;
    }

    public boolean hasBuriedItem() {
        return buriedItem != null;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        StringBuilder desc = new StringBuilder(description);
        if (!items.isEmpty()) {
            desc.append("\nItems here: ");
            for (Item item : items) {
                desc.append(item.getName()).append(" ");
            }
        }
        if (npc != null && !npc.isDefeated()) {
            desc.append("\nYou see ").append(npc.getName()).append(" here.");
        }
        return desc.toString();
    }

    public boolean isPassable() {
        return isPassable;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }
}
