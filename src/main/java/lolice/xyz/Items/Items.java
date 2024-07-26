package lolice.xyz.Items;

public class Items {
    private final String name;
    private final String description;
    private final int sellPrice;
    private final int buyPrice;
    private final boolean stackable;

    public Items(String name, String description, int sellPrice, int buyPrice,boolean stackable) {
        this.name = name;
        this.description = description;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.stackable = stackable;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public boolean isStackable() {
        return stackable;
    }

    public static class Weapon extends Items {
        private final int damage;
        private final int durability;
        private final int level;
        private final int requiredLevel;

        public Weapon(String name, String description, int sellPrice,int buyPrice, boolean stackable, int damage, int durability, int level, int requiredLevel) {
            super(name, description, sellPrice, buyPrice, stackable);
            this.damage = damage;
            this.durability = durability;
            this.level = level;
            this.requiredLevel = requiredLevel;
        }

        //getters
        public int getDamage() {
            return damage;
        }

        public int getDurability() {
            return durability;
        }

        public int getLevel() {
            return level;
        }

        public int getRequiredLevel() {
            return requiredLevel;
        }
    }

    public static class Armor extends Items {
        private final int defense;
        private final int durability;
        private final int level;
        private final int requiredLevel;

        public Armor(String name, String description, int sellPrice,int buyPrice, boolean stackable, int defense, int durability, int level, int requiredLevel) {
            super(name, description, sellPrice, buyPrice, stackable);
            this.defense = defense;
            this.durability = durability;
            this.level = level;
            this.requiredLevel = requiredLevel;
        }

        //getters
        public int getDefense() {
            return defense;
        }

        public int getDurability() {
            return durability;
        }

        public int getLevel() {
            return level;
        }

        public int getRequiredLevel() {
            return requiredLevel;
        }
    }
}
