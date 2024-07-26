package lolice.xyz.Players;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Items.Inventory;
import lolice.xyz.NPC.NPC;
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
    private List<Location> locations;
    private Inventory inventory;

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
        this.locations = new ArrayList<>();
        this.inventory = new Inventory(10);
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

    public List<Location> getLocations() {
        return locations;
    }

    public Inventory getInventory() {
        return inventory;
    }

    // Setters

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
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
    public Location getCurrentLocation() {
        for (Location location : this.locations) {
            if (location.getX() == x && location.getY() == y) {
                return location;
            }
        } return new Location("Location not found", 0, 0, false, "Null");
    }


    //Show adjacent locations
    public void showAdjacentLocations(List<Location> locationList) {
        System.out.println("Adjacent locations: ");
        for (Location location : locationList) {
            if (location.getX() == x && location.getY() == y + 1) {
                if(location.isUnlocked()) {
                    System.out.println("North: " + location.getLocationName() + " (Unlocked)");
                } else {
                    System.out.println("North: " + location.getLocationName() + " (Locked)");
                }

            }
            if (location.getX() == x && location.getY() == y - 1) {
                if(location.isUnlocked()) {
                    System.out.println("South: " + location.getLocationName() + " (Unlocked)");
                } else {
                    System.out.println("South: " + location.getLocationName() + " (Locked)");
                }
            }
            if (location.getX() == x + 1 && location.getY() == y) {
                if(location.isUnlocked()) {
                    System.out.println("East: " + location.getLocationName() + " (Unlocked)");
                } else {
                    System.out.println("East: " + location.getLocationName() + " (Locked)");
                }
            }
            if (location.getX() == x - 1 && location.getY() == y) {
                if(location.isUnlocked()) {
                    System.out.println("West: " + location.getLocationName() + " (Unlocked)");
                } else {
                    System.out.println("West: " + location.getLocationName() + " (Locked)");
                }
            }
        }
    }

    public List<Location> getAdjacentLocations(List<Location> locationList) {
        List<Location> adjacentLocations = new ArrayList<>();
        for (Location location : locationList) {
            if (location.getX() == x && location.getY() == y + 1) {
                adjacentLocations.add(location);
            }
            if (location.getX() == x && location.getY() == y - 1) {
                adjacentLocations.add(location);
            }
            if (location.getX() == x + 1 && location.getY() == y) {
                adjacentLocations.add(location);
            }
            if (location.getX() == x - 1 && location.getY() == y) {
                adjacentLocations.add(location);
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

    public void playerMove() {
        while(true) {
            Scanner UserChoice = new Scanner(System.in);
            System.out.println("Which way do you want to explore ?");
            this.showAdjacentLocations(this.locations);
            System.out.println("Enter your choice: ");
            String Direction = UserChoice.next();

            if(Direction.equals("North")) {
                //Check if unlocked parameter is equal to false
                this.moveNorth();
                if (!this.getCurrentLocation().isUnlocked()) {
                    System.out.println("You can't go that way, the location is currently locked \n");
                    this.moveSouth();
                    break;
                }
            } else if (Direction.equals("South")) {
                this.moveSouth();
                if (!this.getCurrentLocation().isUnlocked()) {
                    System.out.println("You can't go that way, the location is currently locked \n");
                    this.moveNorth();
                    break;
                }
            } else if (Direction.equals("East")) {
                this.moveEast();
                if (!this.getCurrentLocation().isUnlocked()) {
                    System.out.println("You can't go that way, the location is currently locked \n");
                    this.moveWest();
                    break;
                }
            } else if (Direction.equals("West")) {
                this.moveWest();
                if (!this.getCurrentLocation().isUnlocked()) {
                    System.out.println("You can't go that way, the location is currently locked \n");
                    this.moveEast();
                    break;
                }
            } else {
                System.out.println("Invalid choice");
            }
            if (this.getCurrentLocation().getLocationName().equals("Location not found")) {
                System.out.println("You can't go that way, location does not exist. \n");

            } else {
                System.out.println("You are now in: " + this.getCurrentLocation().getLocationName() + "\n");
                break;
            }
        }
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
                System.out.println(quests.getMission());
                System.out.println("Progress: " + quests.getCondition() + "/" + quests.getConditionGoal() + "\n");
            }
        }
    }

    //Update all quests
    public void updateAllQuests(Enemy_init enemy) {
        if (activeQuests.isEmpty()) {
            return;
        }
        for(Quest quest : activeQuests) {
            quest.defeatEnemyCondition(enemy, this);
        }
    }

    public void talkToNPC() {
        System.out.println("Who do you want to talk to?");
        if (this.getCurrentLocation() instanceof Location.Village) {
            ((Location.Village) this.getCurrentLocation()).showNPCInfo();

            Scanner UserChoice2 = new Scanner(System.in);
            System.out.println("Enter your choice: ");
            String Choice2 = UserChoice2.nextLine();


            for (NPC npc : ((Location.Village) this.getCurrentLocation()).getNPC()) {
                if (Choice2.equals(npc.getName())) {
                    this.npcDialog(npc);
                }else {System.out.println("There's no NPC named: " + Choice2);}
            }
        }
    }

    public void unlockLocation(String locationName) {
        for(Location location : this.locations) {
            if(location.getLocationName().equals(locationName)) {
                location.setUnlocked();
            }
        }
    }

    public void acceptQuest(NPC npc, int choice) {
            if (npc.getQuest() != null) {
                Quest quest = npc.getQuest().get(choice - 1);
                quest.showQuestInfo();
                Scanner UserChoice3 = new Scanner(System.in);
                System.out.println("Do you want to accept the quest?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your choice: ");
                int Choice3 = UserChoice3.nextInt();
                if (Choice3 == 1) {
                    npc.questAccepted(this, quest);
                } else if (Choice3 == 2) {
                    System.out.println("Quest declined");
                } else {
                    System.out.println("Invalid choice");
                }
            }
        }

        public void npcShop(NPC npc) {
            npc.getShop().showShop();
            if(!npc.getShop().isEmpty()) {
                System.out.println("You have " + this.getGold() + " gold");

                Scanner UserChoice4 = new Scanner(System.in);
                System.out.println("Do you want to buy something?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                if(UserChoice4.nextInt() == 1) {
                    npc.getShop().buyItem(this);
                    npcDialog(npc);
                }
                else if(UserChoice4.nextInt() == 2) {
                    System.out.println("You left the shop");
                    npcDialog(npc);
                } else {System.out.println("Invalid choice");}
            }
        }



        public void npcDialog(NPC npc){
            System.out.println(npc.getDialog());
            Scanner UserChoice3 = new Scanner(System.in);
            System.out.println("What do you want to do?");
            System.out.println("1. Show quests");
            System.out.println("2. Enter shop");
            System.out.println("0. Exit");

            int choice = UserChoice3.nextInt();

            if(choice == 1) {
                this.choiceQuest(npc);
            }
            else if(choice == 2) {
                this.npcShop(npc);
            }
            else if(choice == 0) {
                System.out.println("You left the NPC");
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    public void choiceQuest(NPC npc) {
        System.out.println("Which quest do you want to choose?");
        int count = 0;
        for (Quest quest : npc.getQuest()) {
            count++;
            System.out.println(count + quest.getName());
        }
        Scanner UserChoice = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        int Choice = UserChoice.nextInt();
        this.acceptQuest(npc, Choice);
    }
}





