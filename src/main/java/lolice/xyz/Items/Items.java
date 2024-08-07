package lolice.xyz.Items;

import lolice.xyz.Skill.Skill;

import java.io.Serial;
import java.io.Serializable;

public class Items implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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

        public static class mageWeapon extends Weapon {
            private final int mana;
            private final int requiredMana;
            private final Skill weaponSkill;

            //I use 2 constructors here to make it easier to create a weapon with or without a skill
            public mageWeapon(String name, String description, int sellPrice, int buyPrice, boolean stackable, int damage, int durability, int level, int requiredLevel, int mana, int requiredMana) {
                this(name, description, sellPrice, buyPrice, stackable, damage, durability, level, requiredLevel, mana, requiredMana, null);
            }


            public mageWeapon(String name, String description, int sellPrice, int buyPrice, boolean stackable, int damage, int durability, int level, int requiredLevel, int mana, int requiredMana, Skill weaponSkill) {
                super(name, description, sellPrice, buyPrice, stackable, damage, durability, level, requiredLevel);
                this.mana = mana;
                this.requiredMana = requiredMana;
                this.weaponSkill = weaponSkill;
            }

            //getters
            public int getMana() {
                return mana;
            }

            public int getRequiredMana() {
                return requiredMana;
            }

            public Skill getWeaponSkill() {
                return weaponSkill;
            }
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

        public static class Helmet extends Armor {
            private final int health;
            private final int defense;

            public Helmet(String name, String description, int sellPrice, int buyPrice, boolean stackable, int defense, int durability, int level, int requiredLevel, int health, int requiredHealth) {
                super(name, description, sellPrice, buyPrice, stackable, defense, durability, level, requiredLevel);
                this.health = health;
                this.defense = requiredHealth;
            }

            //getters
            public int getHealth() {
                return health;
            }

            public int getDefense() {
                return defense;
            }

        }
    }

    public static class SkillBook extends Items {
        private final Skill skill;

        public SkillBook(String name, String description, int sellPrice, int buyPrice, boolean stackable, Skill skill) {
            super(name, description, sellPrice, buyPrice, stackable);
            this.skill = skill;
        }

        //getters
        public Skill getSkill() {
            return skill;
        }
    }

    public static class Potion extends Items {
        private final int health;
        private final int mana;

        public Potion(String name, String description, int sellPrice, int buyPrice, boolean stackable, int health, int mana) {
            super(name, description, sellPrice, buyPrice, stackable);
            this.health = health;
            this.mana = mana;
        }

        //getters
        public int getHealth() {
            return health;
        }

        public int getMana() {
            return mana;
        }
    }
}
