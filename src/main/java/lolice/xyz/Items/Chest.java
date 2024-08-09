package lolice.xyz.Items;

import java.util.List;
import java.util.Scanner;
import java.util.Random;

import lolice.xyz.Items.Items.Key;
import lolice.xyz.Players.Characters_init;

public class Chest {
    private final String name;
    private final List<Items> items;
    private final int gold;
    private final boolean opened;
    private final boolean keyNeeded;
    private final Key key;

    public Chest(String name, List<Items> items, int gold, boolean keyNeeded, Key key) {
        this.name = name;
        this.items = items;
        this.gold = gold;
        this.key = key;
        this.opened = false;
        this.keyNeeded = keyNeeded;
    }

    public String getName() {
        return name;
    }

    public List<Items> getItems() {
        return items;
    }

    public int getGold() {
        return gold;
    }

    public boolean isKeyNeeded() {
        return keyNeeded;
    }

    public boolean isOpened() {
        return opened;
    }

    public Key getKey() {
        return key;
    }

    public void openChest(Characters_init player) {
        System.out.println("Chest type: " + this.getName());
        System.out.println("Do you want to open the chest ? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("y")) {
            if (keyNeeded) {
                for (Items item : player.getInventory().getItems()) {
                    if (item.getName().equals(this.getKey().getName())) {
                        System.out.println("You used the key to open the chest");
                        player.getInventory().removeItem(item);
                        for (Items chestItem : this.getItems()) {
                            player.getInventory().addItem(chestItem);
                        }
                        player.setGold(player.getGold() + this.getGold());
                    }
                }
            }
        } else {
            System.out.println("You didn't open the chest");
        }
    }

    public void addItem(Items item) {
        this.items.add(item);
    }

    public void generateItems() {
        int quality;
        switch (this.name){
            case "Small chest":
                int b = new Random().nextInt(10);
                if(b < 9) {
                    quality = 1;
                }
                else {
                    quality = 2;
                }
                while (true) {
                    int c = new Random().nextInt(10);
                    if(c < 9) {
                        this.addItem(GameObjects.getRandomItem(quality));
                        break;
                    } else {
                        this.addItem(GameObjects.getRandomItem(quality));
                    }
                }
                break;

            case "Wooden chest":
                int d = new Random().nextInt(10);
                if(d < 7) {
                    quality = 1;
                }
                else {
                    quality = 2;
                }
                while (true) {
                    int e = new Random().nextInt(10);
                    if(e < 9) {
                        this.addItem(GameObjects.getRandomItem(quality));
                        break;
                    } else {
                        this.addItem(GameObjects.getRandomItem(quality));
                    }
                }
                break;

            case "Big chest":
                int f = new Random().nextInt(10);
                if(f < 5) {
                    quality = 1;
                }
                else {
                    quality = 2;
                }
                while (true) {
                    int g = new Random().nextInt(10);
                    if(g < 9) {
                        this.addItem(GameObjects.getRandomItem(quality));
                        break;
                    } else {
                        this.addItem(GameObjects.getRandomItem(quality));
                    }
                }
                break;
        }
    }


}
