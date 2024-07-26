package lolice.xyz.Items;

import lolice.xyz.Players.Characters_init;

import java.util.Scanner;

public class Inventory {
    private final Items[] items;
    private final int size;
    private int count;

    public Inventory(int size) {
        this.size = size;
        this.items = new Items[size];
        this.count = 0;
    }

    public void addItem(Items item) {
        if (count < size) {
            items[count] = item;
            count++;
        } else {
            System.out.println("Inventory is full!");
        }
    }

    public void removeItem(Items item) {
        // Remove the item from the inventory
        for (int i = 0; i < count; i++) {
            if (items[i].equals(item)) {
                items[i] = null;
                count--;
                //Adjust the array so there are no null values
                for (int j = i; j < count; j++) {
                    items[j] = items[j + 1];
                }
                items[count] = null;
                break;
            }
        }
    }

    public void showInventory() {
        if (count == 0) {
            System.out.println("Inventory is empty!");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(items[i].getName());
            }
        }
    }

    public static class Shop extends Inventory {
        private final Items[] items;
        private final int size;
        private int count;

        public Shop(int size) {
            super(size);
            this.size = size;
            this.items = new Items[size];
            this.count = 0;
        }

        public void showShop() {
            if (count == 0) {
                System.out.println("Shop is empty!");
            } else {
                for (int i = 0; i < count; i++) {
                    System.out.println(items[i].getName());
                }
            }
        }

        public Boolean isEmpty() {
            return count == 0;
        }
        public void buyItem(Characters_init player) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name of the item you want to buy: ");
            String itemName = scanner.nextLine();
            for (int i = 0; i < count; i++) {
                if (items[i].getName().equals(itemName)) {
                    if (player.getGold() >= items[i].getBuyPrice()) {
                        player.setGold(player.getGold() - items[i].getBuyPrice());
                        player.getInventory().addItem(items[i]);
                        System.out.println("You bought " + items[i].getName());
                    } else {
                        System.out.println("Not enough gold!");
                    }
                    break;
                } else{System.out.println("There's no item named"+ itemName);}
            }
        }
    }
}
