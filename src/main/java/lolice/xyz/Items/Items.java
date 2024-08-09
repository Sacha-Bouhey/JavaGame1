package lolice.xyz.Items;

import lolice.xyz.Players.Characters_init;
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
    private final int quantity;
    private final int quality;

    public Items(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality) {
        this.name = name;
        this.description = description;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.stackable = stackable;
        this.quantity = 1;
        this.quality = quality;
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

    public int getQuantity() {
        return quantity;
    }

    public int getQuality() {
        return quality;
    }

    public static class Weapon extends Items {
        private final int damage;
        private final int durability;
        private final int level;
        private final int requiredLevel;

        public Weapon(String name, String description, int sellPrice,int buyPrice, boolean stackable, int quality, int damage, int durability, int level, int requiredLevel) {
            super(name, description, sellPrice, buyPrice, stackable, quality);
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
            public mageWeapon(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, int damage, int durability, int level, int requiredLevel, int mana, int requiredMana) {
                this(name, description, sellPrice, buyPrice, stackable, quality, damage, durability, level, requiredLevel, mana, requiredMana, null);
            }


            public mageWeapon(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, int damage, int durability, int level, int requiredLevel, int mana, int requiredMana, Skill weaponSkill) {
                super(name, description, sellPrice, buyPrice, stackable, quality, damage, durability, level, requiredLevel);
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

        public Armor(String name, String description, int sellPrice,int buyPrice, boolean stackable, int quality, int defense, int durability, int level, int requiredLevel) {
            super(name, description, sellPrice, buyPrice, stackable, quality);
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

            public Helmet(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, int defense, int durability, int level, int requiredLevel, int health, int requiredHealth) {
                super(name, description, sellPrice, buyPrice, stackable, quality, defense, durability, level, requiredLevel);
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

        public static class Chestplate extends Armor {
            private final int health;
            private final int defense;

            public Chestplate(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, int defense, int durability, int level, int requiredLevel, int health, int requiredHealth) {
                super(name, description, sellPrice, buyPrice, stackable, quality, defense, durability, level, requiredLevel);
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

        public static class Leggings extends Armor {
            private final int health;
            private final int defense;

            public Leggings(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, int defense, int durability, int level, int requiredLevel, int health, int requiredHealth) {
                super(name, description, sellPrice, buyPrice, stackable, quality, defense, durability, level, requiredLevel);
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

        public static class Boots extends Armor {
            private final int health;
            private final int defense;

            public Boots(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, int defense, int durability, int level, int requiredLevel, int health, int requiredHealth) {
                super(name, description, sellPrice, buyPrice, stackable, quality, defense, durability, level, requiredLevel);
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

        public static class MageArmor extends Armor{
            private final int mana;
            private final int requiredMana;

            public MageArmor(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, int defense, int durability, int level, int requiredLevel, int mana, int requiredMana) {
                super(name, description, sellPrice, buyPrice, stackable, quality, defense, durability, level, requiredLevel);
                this.mana = mana;
                this.requiredMana = requiredMana;
            }

            //getters
            public int getMana() {
                return mana;
            }

            public int getRequiredMana() {
                return requiredMana;
            }

            public static class MageRobe extends MageArmor {
                private final int health;

                public MageRobe(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, int defense, int durability, int level, int requiredLevel, int mana, int requiredMana, int health) {
                    super(name, description, sellPrice, buyPrice, stackable, quality, defense, durability, level, requiredLevel, mana, requiredMana);
                    this.health = health;
                }

                //getters
                public int getHealth() {
                    return health;
                }
            }
        }
    }

    public static class SkillBook extends Items {
        private final Skill skill;

        public SkillBook(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, Skill skill) {
            super(name, description, sellPrice, buyPrice, stackable, quality);
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

        public Potion(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality, int health, int mana) {
            super(name, description, sellPrice, buyPrice, stackable, quality);
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

    public static class Key extends Items {
        public Key(String name, String description, int sellPrice, int buyPrice, boolean stackable, int quality) {
            super(name, description, sellPrice, buyPrice, stackable, quality);
        }


    }
}
