import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
            System.out.println(item.getName() + " has been added to your inventory.");
        }
    }

    public void removeItem(String itemName) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public boolean hasItem(String itemName) {
        return items.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public void showInventory() {
        System.out.println("Your Inventory:");
        if (items.isEmpty()) {
            System.out.println(" - Empty");
        } else {
            for (Item item : items) {
                System.out.println(" - " + item.getName() + ": " + item.getDescription());
            }
        }
    }

    public Item getItemByName(String name) {
        return items.stream().filter(i -> i.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<Item> getItems() {
        return items;
    }
}
