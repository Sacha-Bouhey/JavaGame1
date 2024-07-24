package lolice.xyz.Players;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Skill;
import lolice.xyz.Skill_stats;
import lolice.xyz.Location;
import lolice.xyz.NPC.Quest;

import java.util.*;


public class Characters_init{
    private String name;
    private int maxhealth;
    private int health;
    private int strength;
    private int mana;
    private int agility;
    private int defence;
    private int statpoint;
    private List<Skill> skills;
    private Leveling leveling;
    private List<Skill_stats> skill_stats;
    private int gold;
    private List<Quest> activeQuests;
    private int x;
    private int y;

    public Characters_init(String Cname, int Cmaxhealth, int Cstrength, int Cmana, int Cagility, int Cdefence, int Cstatpoint,List<Skill> skills, int x, int y) {
        this.name = Cname;
        this.maxhealth = Cmaxhealth;
        this.health = Cmaxhealth;
        this.strength = Cstrength;
        this.mana = Cmana;
        this.agility = Cagility;
        this.defence = Cdefence;
        this.statpoint = Cstatpoint;
        this.skills = skills;
        this.leveling = new Leveling(0, 1);
        this.skill_stats = new ArrayList<>();
        skill_stats.add(new Skill_stats("Staff", name, 0));
        skill_stats.add(new Skill_stats("Books", name, 0));
        skill_stats.add(new Skill_stats("Magic gauntlet", name, 0));
        this.x = x;
        this.y = y;
        this.gold = 0;
        this.activeQuests = new ArrayList<>();
    }

    //Getters
    public String getName(){
        return this.name;
    }

    public int getMaxhealth() {
        return maxhealth;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getMana() {
        return mana;
    }

    public int getAgility() {
        return agility;
    }

    public int getDefence() {
        return defence;
    }

    public int getStatpoint() {
        return statpoint;
    }
    public Leveling getLeveling() {
        return leveling;
    }

    public List<Skill_stats> getSkill_stats() {
        return skill_stats;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public int getGold() {
        return gold;
    }
    
    public List<Quest> getActiveQuests() {
        return activeQuests;
    }

    // Setters
    public void setMaxHealth(int newMaxHealth) {
        this.maxhealth = newMaxHealth;
    }
    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public void setStrength(int newStrength) {
        this.strength = newStrength;
    }

    public void setMana(int newMana) {
        this.mana = newMana;
    }

    public void setAgility(int newAgility) {
        this.agility = newAgility;
    }

    public void setDefence(int newDefence) {
        this.defence = newDefence;
    }

    public void setStatpoint(int newStatpoint) {
        this.statpoint = newStatpoint;
    }

    public void TakeDamage(int damage) {
        health -= damage - defence;
    }

    public void setLeveling(Leveling leveling) {
        this.leveling = leveling;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }

    public void setActiveQuests(List<Quest> activeQuests) {
        this.activeQuests = activeQuests;
    }

    //Show info
    public void showInfo() {
        System.out.println("Name: " + name + "\n");
        System.out.println("Level: " + leveling.getLevel() + "\n");
        System.out.println("Exp: " + leveling.getExp() +"/" + leveling.getExptolevel() + "\n");
        System.out.println("Health: " + health + "/" + maxhealth + "\n");
        System.out.println("Strength: " + strength + "\n");
        System.out.println("Mana: " + mana + "\n");
        System.out.println("Agility: " + agility + "\n");
        System.out.println("Defence: " + defence + "\n");
        System.out.println("Statpoint: " + statpoint + "\n");
    }

    //Check if enough mana
    public boolean checkMana(int manaCost) {
        return mana >= manaCost;
    }

    //Use skill
    public int useSkill(Skill player_skill) {
        //Use skill
        int damage = player_skill.getDamage();
        //If skill doesn't cost mana, add strength to damage
        if(player_skill.getManaCost() == 0)
            damage += this.strength;
        //If skill costs mana, remove mana
        else {
            this.mana -= player_skill.getManaCost();
        }
        return damage;
    }

    //Show current location
    public Location getCurrentLocation(Map<String, Location> Location) {
        for (Map.Entry<String, Location> entry : Location.entrySet()) {
            if (entry.getValue().x == x && entry.getValue().y == y) {
                return entry.getValue();
            }
        } return new Location("Location not found", 0, 0, false, "Null");
    }


    //Show adjacent locations
    public void showAdjacentLocations(Map<String, Location> Location) {
        for (Map.Entry<String, Location> entry : Location.entrySet()) {
            if (entry.getValue().x == x && entry.getValue().y == y + 1) {
                if(entry.getValue().isUnlocked()) {
                    System.out.println("North: " + entry.getKey() + " Unlocked");
                } else {
                    System.out.println("North: " + entry.getKey() + " Locked");
                }

            }
            if (entry.getValue().x == x && entry.getValue().y == y - 1) {
                if(entry.getValue().isUnlocked()) {
                    System.out.println("South: " + entry.getKey() + " Unlocked");
                } else {
                    System.out.println("South: " + entry.getKey() + " Locked");
                }
            }
            if (entry.getValue().x == x + 1 && entry.getValue().y == y) {
                if(entry.getValue().isUnlocked()) {
                    System.out.println("East: " + entry.getKey() + " Unlocked");
                } else {
                    System.out.println("East: " + entry.getKey() + " Locked");
                }
            }
            if (entry.getValue().x == x - 1 && entry.getValue().y == y) {
                if(entry.getValue().isUnlocked()) {
                    System.out.println("West: " + entry.getKey() + " Unlocked");
                } else {
                    System.out.println("West: " + entry.getKey() + " Locked");
                }
            }
        }
    }
    public List<Location> getAdjacentLocations(Map<String, Location> Location) {
        List<Location> adjacentLocations = new ArrayList<>();
        for (Map.Entry<String, Location> entry : Location.entrySet()) {
            if (entry.getValue().x == x && entry.getValue().y == y + 1) {
                adjacentLocations.add(entry.getValue());
            }
            if (entry.getValue().x == x && entry.getValue().y == y - 1) {
                adjacentLocations.add(entry.getValue());
            }
            if (entry.getValue().x == x + 1 && entry.getValue().y == y) {
                adjacentLocations.add(entry.getValue());
            }
            if (entry.getValue().x == x - 1 && entry.getValue().y == y) {
                adjacentLocations.add(entry.getValue());
            }
        }
        return adjacentLocations;
    }

    //Move
    public void moveNorth() {
        y += 1;
    }
    public void moveSouth() {
        y -= 1;
    }
    public void moveEast() {
        x += 1;
    }
    public void moveWest() {
        x -= 1;
    }

    //Add active quest
    public void addActiveQuest(Quest quest) {
        activeQuests.add(quest);
    }

    //Print current active guests
    public void showActiveQuests() {
        for (Quest quests : activeQuests) {
            if (!quests.isCompleted()) {
                System.out.println(quests.getName());
            }
        }
    }

    //Update all quests
    public void updateAllQuests(Enemy_init enemy) {
        if (activeQuests.isEmpty()) {
            return;
        }
        for(Quest quest : activeQuests) {
            quest.defeatEnemyCondition(enemy);
        }
    }
}

