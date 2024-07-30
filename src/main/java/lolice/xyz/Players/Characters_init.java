package lolice.xyz.Players;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Items.Inventory;
import lolice.xyz.Items.Items;
import lolice.xyz.NPC.NPC;
import lolice.xyz.Skill.Effect;
import lolice.xyz.Skill.Skill;
import lolice.xyz.Skill_stats;
import lolice.xyz.Location;
import lolice.xyz.NPC.Quest;

import java.util.*;


public class Characters_init{
    private String name;
    private int maxhealth;
    private int health;
    private int maxstrength;
    private int strength;
    private int maxmana;
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
    private final Inventory inventory;
    private Items activeItem;
    private boolean isStunned;

    public Characters_init(String Cname, int Cmaxhealth, int Cmaxstrength, int Cmaxmana, int Cagility, int Cdefence, int Cstatpoint,List<Skill> skills, int x, int y) {
        this.name = Cname;
        this.maxhealth = Cmaxhealth;
        this.health = Cmaxhealth;
        this.maxstrength = Cmaxstrength;
        this.strength = Cmaxstrength;
        this.maxmana = Cmaxmana;
        this.mana = Cmaxmana;
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
        this.isStunned = false;
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

    public int getMaxStrength() {
        return maxstrength;
    }

    public int getStrength() {
        return strength;
    }

    public int getMaxMana() {
        return maxmana;
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

    public List<Quest> getFinishedQuest() {
        List<Quest> finishedQuests = new ArrayList<>();
        for(Quest quest : activeQuests) {
            if(quest.isCompleted()) {
                finishedQuests.add(quest);
            }
        }
        return finishedQuests;
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

    public void setMaxStrength(int newMaxStrength) {
        this.maxstrength = newMaxStrength;
    }

    public void setStrength(int newStrength) {
        this.strength = newStrength;
    }
    
    public void setMaxMana(int newMaxMana) {
        this.maxmana = newMaxMana;
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

    public void setStunned(Boolean isstunned) {
        this.isStunned = isstunned;
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
    public int useSkill(Skill player_skill, Enemy_init enemy) {
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
                try {
                    int Choice3 = UserChoice3.nextInt();
                    if (Choice3 == 1) {
                        npc.questAccepted(this, quest);
                    } else if (Choice3 == 2) {
                        System.out.println("Quest declined");
                    } else {
                        System.out.println("Invalid choice");
                    }
                } catch (InputMismatchException e) {
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
            boolean hasFinishedQuest = false;
            System.out.println(npc.getDialog());
            Scanner UserChoice3 = new Scanner(System.in);
            System.out.println("What do you want to do?");
            System.out.println("1. Show quests");
            System.out.println("2. Enter shop");



            if(!getFinishedQuest().isEmpty()) {

                for(Quest quest : getFinishedQuest()) {
                    if (quest.getOrigin().equals(npc.getName())) {
                        hasFinishedQuest = true;
                        break;
                    }
                }
                if(hasFinishedQuest) {
                    System.out.println("3. Return quest");
                }
            }


            System.out.println("0. Exit");

            int choice = UserChoice3.nextInt();

            if(choice == 1) {
                if(!npc.getQuest().isEmpty()){
                    this.choiceQuest(npc);
                } else{System.out.println("No quests currently available \n");}
            }
            else if(choice == 2) {
                this.npcShop(npc);
            }
            if(hasFinishedQuest) {
                if(choice == 3) {
                    for(Quest quest : getFinishedQuest()) {
                        if (quest.getOrigin().equals(npc.getName())) {
                            System.out.println("You returned the quest: " + quest.getName());
                            quest.checkQuestCompletion(this);
                        }
                    }
                }
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
            System.out.println(count + " " + quest.getName());
        }
        Scanner UserChoice = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        int Choice = UserChoice.nextInt();
        this.acceptQuest(npc, Choice);
    }


    public void equipItem(Items item) {
        if(item instanceof Items.Weapon.mageWeapon) {
            if(this.activeItem != null) {
                System.out.println("You already have an active item equipped, do you want to replace it?");
                Scanner UserChoice = new Scanner(System.in);
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your choice: ");
                try {
                    int choice = UserChoice.nextInt();
                    if (choice == 1) {
                        this.inventory.addItem(this.activeItem);
                        this.activeItem = item;
                        System.out.println("You equipped: " + item.getName());
                    } else if (choice == 2) {
                        System.out.println("You didn't equip the item");
                    } else {
                        System.out.println("Invalid choice");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice 2");
                }
            } else {this.activeItem = item; System.out.println("You equipped: " + item.getName()); this.inventory.removeItem(item);}
        }
    }

    public void unequipItem() {
        if (this.activeItem != null) {
            System.out.println("You unequipped: " + this.activeItem.getName());
            this.inventory.addItem(this.activeItem);
            this.activeItem = null;
        } else{System.out.println("You dont have any equipped item");}
    }


    public void showInventoryTutorial() {
        System.out.println("This is the inventory menu, here you can manage your items");
        System.out.println("You can use items, drop items, equip items and unequip items");
        System.out.println("To use an item, select 1, then type the name of the item and press enter. If the item is usable, it will be consumed or durability will decrease by 1.");
        System.out.println("To drop an item, select 2, then type the name of the item and press enter. The item will be removed from your inventory and you will not be able to recover it.");
        System.out.println("To equip an item, select 3, then type the name of the item and press enter. If the item is equippable, it will be equipped and replace the currently equipped item.\n" +
                "The item you equip will disappear from your inventory. To add it again you will have to unequip it first.\n" +
                "If you have an equipped item and you equip another one, the equipped item will be added to your inventory, replacing the new equipped item.");
        System.out.println("To unequip an item, select 4. The item will be removed from your equipped items and added to your inventory.");

    }

    public void useItem() {
        System.out.println("Witch item do you want to use ?");
        for(Items items : this.inventory.getItems()) {
            if(items != null) {
                System.out.println(items.getName());
            }
        }
        Scanner UserChoice = new Scanner(System.in);
        int choice = UserChoice.nextInt();
        Items item = this.inventory.getItems()[choice];
        if(item instanceof Items.Potion) {
            Items.Potion potion = (Items.Potion) item;
            this.health += potion.getHealth();
            if(this.health > this.maxhealth) {
                this.health = this.maxhealth;
            }
            this.mana += potion.getMana();
            if(this.mana > this.maxmana) {
                this.mana = this.maxmana;
            }
            this.inventory.removeItem(item);
        }
    }


    public void inventoryMenu() {
        inventory.showInventory();
        Scanner UserChoice = new Scanner(System.in);
        System.out.println("This is the inventory menu, what do you want to do?");
        System.out.println("1. Use item (WIP)");
        System.out.println("2. Drop item (WIP)");
        System.out.println("3. Equip item");
        System.out.println("4. Unequip item");
        System.out.println("5. Show tutorial");
        System.out.println("0. Exit");
        while(true) {
            try {
                System.out.println("What do you want to do ? ");
                int choice = UserChoice.nextInt();
                if (choice == 1) {
                    //this.useItem();

                } else if (choice == 2) {
                    //this.dropItem();

                } else if (choice == 3) {
                    System.out.println("Which item do you want to equip?");
                    for (int i = 0; i < this.inventory.getItems().length; i++) {
                        if (this.inventory.getItems()[i] != null) {
                            System.out.println(i + ". " + this.inventory.getItems()[i].getName());
                        }
                    }
                    int userChoice = UserChoice.nextInt();
                    Items item = this.inventory.getItems()[userChoice];
                    this.equipItem(item);
                    break;

                } else if (choice == 4) {
                    this.unequipItem();

                } else if (choice == 5) {
                    this.showInventoryTutorial();

                } else if (choice == 0) {
                    System.out.println("You left the inventory menu");
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice");
            }

        }
    }

    public void showQuestMenu() {
        int count = 0;
        for (Quest quest : activeQuests) {
            if (quest.isCompleted()) {
                if(count == 0) {
                    System.out.println("Completed quests");
                    count++;
                }
                System.out.println(quest.getName() + " Return quest to: " + quest.getOrigin());
            }
        }
        for (Quest quests : activeQuests) {
            if (!quests.isCompleted()) {
                System.out.println(quests.getName());
                System.out.println(quests.getMission());
                System.out.println("Progress: " + quests.getCondition() + "/" + quests.getConditionGoal() + "\n");
            }
        }
    }

    public void stunEnemy(Enemy_init enemy) {
        enemy.setStunned(true);
    }
}





