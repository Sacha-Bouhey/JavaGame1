package lolice.xyz.Items;

import lolice.xyz.Skill.Skill;

import java.util.*;

public class GameObjects {
    public static Map<Integer, List<Items>> items = new HashMap<>();

    // Potions
    public static final Items.Potion SMALL_HEALING_POTION = new Items.Potion("Small healing potion", "Heals you for 20 HP", 0, 0, false, 1, 20, 0);

    // Keys
    public static final Items.Key WOODEN_KEY = new Items.Key("Wooden key", "Opens a wooden chest", 5, 20, false, 1);

    //Weapons
    //Mage weapons
    public static final Items.Weapon.mageWeapon WOODEN_STAFF = new Items.Weapon.mageWeapon("Wooden staff", "A basic staff for beginners", 10, 25, false, 2, 2, 100, 1, 1, 10, 100);
    public static final Items.Weapon.mageWeapon OLD_BOOK = new Items.Weapon.mageWeapon("Old book", "An old book full of mystery", 10, 100, false, 2, 1, 150, 1, 3, 20, 120, new Skill("Word of power", "A forgotten spell, written in draconian language", 42, 24, true, null, false));

    //Armors
    //Helmet
    public static final Items.Armor LEATHER_HELMET = new Items.Armor("Leather Helmet", "A basic helmet for beginners", 5, 50, false, 1, 3, 100, 1, 1);

    //Chestplate
    public static final Items.Armor LEATHER_CHESTPLATE = new Items.Armor("Leather Chestplate", "A basic chestplate for beginners", 10, 100, false, 1, 5, 100, 1, 1);

    //Leggings
    public static final Items.Armor LEATHER_LEGGINGS = new Items.Armor("Leather Leggings", "A basic leggings for beginners", 5, 50, false, 1, 3, 100, 1, 1);

    //Boots
    public static final Items.Armor LEATHER_BOOTS = new Items.Armor("Leather Boots", "A basic boots for beginners", 5, 50, false, 1, 3, 100, 1, 1);


    // Chests
    public static final Chest SMALL_CHEST = new Chest("Small chest", List.of(), 0, false, null);
    public static final Chest WOODEN_CHEST = new Chest("Wooden chest", List.of(), 0, true, WOODEN_KEY);
    public static final Chest BIG_CHEST = new Chest("Big chest", List.of(), 0, true, null);


    static {
        items.put(1, new ArrayList<>(Arrays.asList(SMALL_HEALING_POTION, WOODEN_KEY, LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS)));
        items.put(2, new ArrayList<>(Arrays.asList(WOODEN_STAFF, OLD_BOOK)));
    }

    public static Items getRandomItem(int quality) {
        Random random = new Random();
        List<Items> itemList = items.get(quality);
        Items item = itemList.get(random.nextInt(itemList.size()));
        return item;
    }
}
