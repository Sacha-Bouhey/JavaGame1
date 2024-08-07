package lolice.xyz.Items;

import lolice.xyz.Players.Characters_init;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Items> items;
    private final int size;

    public Inventory(int size) {
        this.size = size;
        this.items = new ArrayList<>(size);
    }

    public void addItem(Items item) {
        if (items.size() < size) {
            items.add(item);
        } else {
            System.out.println("Inventory is full!");
        }
    }

    public List<Items> getItems() {
        return items;
    }

    public void removeItem(Items item) {
        items.remove(item);
    }

    public void showInventory() {
        if (this.items.isEmpty()) {
            System.out.println("Inventory is empty!");
        } else {
            for (Items item : items) {
                System.out.println(item.getName());
            }
        }
    }

    public Items getItemByName(String itemName) {
        for (Items item : items) {
            if (itemName.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    public static class Shop extends Inventory {
        private final int size;

        public Shop(int size) {
            super(size);
            this.size = size;
        }

        public Inventory.Shop getShop() {
            return this;
        }

        public void showShop() {
            if (getItems().isEmpty()) {
                System.out.println("Shop is empty!");
            } else {
                System.out.println("\n Shop items:");
                int count = 0;
                for (Items item : getItems()) {
                    System.out.println(count + ": " + item.getName());
                    count++;
                }
                System.out.println("\n");
            }
        }

        public Boolean isEmpty() {
            return getItems().isEmpty();
        }

        public void buyItem(Characters_init player) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name of the item you want to buy: ");
            int itemNumber = scanner.nextInt();
            for (Items item : getItems()) {
                if (itemNumber == getItems().indexOf(item)) {
                    if (player.getGold() >= item.getBuyPrice()) {
                        player.setGold(player.getGold() - item.getBuyPrice());
                        player.getInventory().addItem(item);
                        System.out.println("You bought " + item.getName());
                        player.updateAllQuests(null, item, null);
                        break;
                    } else {
                        System.out.println("Not enough gold!");
                    }
                    break;
                } else {
                    System.out.println("There's no item with number " + itemNumber);
                }
            }
        }
    }
}