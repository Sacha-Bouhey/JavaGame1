package lolice.xyz.Players;

import lolice.xyz.Battle;
import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Items.Inventory;
import lolice.xyz.Items.Items;
import lolice.xyz.NPC.NPC;
import lolice.xyz.Skill.Skill;
import lolice.xyz.Skill_stats;
import lolice.xyz.Map.Location;
import lolice.xyz.NPC.Quest;

import java.io.Serial;
import java.util.*;
import java.io.Serializable;


public class Characters_init implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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
    private final List<Skill> skills;
    private Leveling leveling;
    private final List<Skill_stats> skill_stats;
    private int gold;
    private List<Quest> activeQuests;
    private int x;
    private int y;
    private List<Location> locations;
    private final Inventory inventory;
    private Items activeItem;
    private Map<String, Items.Armor> activeArmor;
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
        this.activeArmor = Collections.emptyMap();
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Map<String, Items.Armor> getActiveArmors() {
        return activeArmor;
    }

    public List<Quest> getFinishedQuest() {
        List<Quest> finishedQuests = new ArrayList<>();
        for(Quest quest : activeQuests) {
            if(quest.isCompleted() && !quest.isReturned()) {
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

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
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

    private Skill_stats getSkill_statsByName(String skill_stats_name){
        for(Skill_stats skill_stats : this.getSkill_stats()) {
            if(skill_stats.getSkill_name().equals(skill_stats_name)) {
                return skill_stats;
            }
        }return null;
    }


    public void playerSkillStatsUpgrade() {
        while (true) {
            System.out.println("What do you want to upgrade?");
            Scanner UserChoice = new Scanner(System.in);
            for (Skill_stats skill_stats : this.getSkill_stats()) {
                if(skill_stats.getClass_name().equals(this.getName())) {
                    System.out.println(skill_stats.getSkill_name() + " level: " + skill_stats.getSkill_level());
                }
            }
            System.out.println("Enter your choice: ");

            int Choice = UserChoice.nextInt();
            Skill_stats skillStats = this.getSkill_stats().get(Choice);
            System.out.println("You have " + this.getStatpoint() + " stat points");
            System.out.println("How many points do you want to use ?");
            int points = UserChoice.nextInt();
            if (this.getStatpoint() > points) {
                skillStats.setSkill_stats(skillStats.getSkill_level() + points);
                this.setStatpoint(this.getStatpoint() - points);
                System.out.println("You upgraded: " + skillStats.getSkill_name() + " to level " + skillStats.getSkill_level());
            } else {
                System.out.println("You don't have enough stat points");
            }
        }
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
    public void updateAllQuests(Enemy_init enemy, Items item, NPC npc) {
        if (activeQuests.isEmpty()) {
            return;
        }
        for(Quest quest : activeQuests) {
            if(!quest.isReturned()) {
                switch (quest.getType()) {
                    case KILL:
                        quest.defeatEnemyCondition(enemy, this);
                        break;

                    case BUY:
                        quest.buyItemCondition(item, this);
                        break;

                    case TALK:
                        quest.talkToCondition(npc, this);
                        break;
                }
            }
        }
    }

    public void talkToNPC() {
        System.out.println("Who do you want to talk to?");

        boolean validInput = false;
        while (!validInput) {
            if (this.getCurrentLocation() instanceof Location.Village) {
                ((Location.Village) this.getCurrentLocation()).showNPCInfo();
                Scanner UserChoice2 = new Scanner(System.in);
                System.out.println("Enter your choice: ");
                String Choice2 = UserChoice2.nextLine();

                for (NPC npc : ((Location.Village) this.getCurrentLocation()).getNPC()) {
                    if (Choice2.equals(npc.getName())) {
                        this.npcDialog(npc);
                        this.updateAllQuests(null, null, npc);
                        validInput = true;
                    }
                }
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
                        System.out.println("Invalid choice. Pleaser enter 1 or 2");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice. Please enter a number.");
                }
            }
        }

        public void npcShop(NPC npc) {
            npc.getShop().showShop();
            if(!npc.getShop().isEmpty()) {
                System.out.println("You have " + this.getGold() + " gold \n");

                Scanner UserChoice4 = new Scanner(System.in);
                System.out.println("Do you want to buy something?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int choice = UserChoice4.nextInt();
                if(choice == 1) {
                    npc.getShop().buyItem(this);
                    npcDialog(npc);
                }
                else if(choice == 2) {
                    System.out.println("You left the shop");
                    npcDialog(npc);
                } else {System.out.println("Invalid choice");}
            }
        }



    public void npcDialog(NPC npc) {
        boolean hasFinishedQuest = false;
        System.out.println(npc.getDialog());
        Scanner userChoiceScanner = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1. Show quests");
        System.out.println("2. Enter shop");

        List<Quest>finishedQuest = getFinishedQuest();

        if (!finishedQuest.isEmpty()) {
            for (Quest quest : finishedQuest){
                if (quest.getOrigin().equals(npc.getName())) {
                    hasFinishedQuest = true;
                    break;
                }
            }
            if (hasFinishedQuest) {
                System.out.println("3. Return quest");
            }
        }

        System.out.println("0. Exit");

        boolean validInput = false;
        while (!validInput) {
            try {

                //Test Integer.parseInt for error catching.
                int choice = Integer.parseInt(userChoiceScanner.nextLine());
                if (choice == 1) {
                    if (!npc.getQuest().isEmpty()) {
                        this.choiceQuest(npc);
                    } else {
                        System.out.println("No quests currently available \n");
                    }
                    validInput = true;
                } else if (choice == 2) {
                    this.npcShop(npc);
                    validInput = true;
                } else if (hasFinishedQuest && choice == 3) {
                    for (Quest quest : finishedQuest) {
                        if (quest.getOrigin().equals(npc.getName())) {
                            System.out.println("You returned the quest: " + quest.getName());
                            quest.checkQuestCompletion(this, npc);
                            quest.setReturned(true);
                        }
                    }
                    validInput = true;
                } else if (choice == 0) {
                    System.out.println("You left the NPC");
                    validInput = true;
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
            }
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
        Items item = this.inventory.getItems().get(choice);
        if(item instanceof Items.Potion potion) {
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
                    for (int i = 0; i < this.inventory.getItems().size(); i++) {
                        if (this.inventory.getItems().get(i) != null) {
                            System.out.println(i + ". " + this.inventory.getItems().get(i).getName());
                        }
                    }
                    int userChoice = UserChoice.nextInt();
                    Items item = this.inventory.getItems().get(userChoice);
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
                System.out.println("Invalid choice 2");
            }

        }
    }

    public void showQuestMenu() {
        int count = 0;
        for (Quest quest : activeQuests) {
            if (quest.isCompleted() && !quest.isReturned()) {
                if(count == 0) {
                    System.out.println("Completed quests");
                    count++;
                }
                System.out.println(quest.getName() + "\n"+ "Return quest to: " + quest.getOrigin() + "\n");
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


    public void exploreLocation() {
        Scanner userChoiceScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("If you are in the wilderness, you will encounter enemies. Are you sure you want to explore? (y/n)");
                String choice = userChoiceScanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    System.out.println("Exploring current location...");
                    this.getCurrentLocation().showLocationInfo();
                    if (this.getCurrentLocation() instanceof Location.Village) {
                        break;
                    } else if (this.getCurrentLocation() instanceof Location.LocationWithEnemies.Wilderness) {
                        List<Enemy_init>enemies = new ArrayList<>();
                        enemies.add(((Location.LocationWithEnemies.Wilderness) this.getCurrentLocation()).selectRandomEnemy(this));
                        Battle battle = new Battle(this, enemies);
                        battle.Start();
                        break;
                    } else if (this.getCurrentLocation() instanceof Location.LocationWithEnemies.Dungeon) {
                        break;
                    }
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("Returning to main menu...");
                    return;
                } else {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("An error occurred: No line found. Please try again.");
                userChoiceScanner = new Scanner(System.in); // Reinitialize the scanner
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        userChoiceScanner.close();
    }

    public void addArmorPiece(Items.Armor armorToEquip) {
        String armorType;
        if(armorToEquip instanceof Items.Armor.Helmet) {
            armorType = "head";
        } else if(armorToEquip instanceof Items.Armor.Chestplate) {
            armorType = "chest";
        } else if(armorToEquip instanceof Items.Armor.Leggings) {
            armorType = "legs";
        } else if(armorToEquip instanceof Items.Armor.Boots) {
            armorType = "feet";
        } else {
            System.out.println("Invalid armor type");
            return;
        }

        if(activeArmor.get(armorType) != null){
            System.out.println("You already have an helmet equipped. Do you want to replace it ?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Warning: if your inventory is full, the item will be gone!");
            while(true) {
                Scanner userChoiceScanner = new Scanner(System.in);
                try {
                    int choice = userChoiceScanner.nextInt();
                    if (choice == 1) {
                        inventory.addItem(activeArmor.get(armorType));
                        System.out.println("You removed item: " + activeArmor.get(armorType).getName());
                        activeArmor.remove(armorType);
                        activeArmor.put(armorType, armorToEquip);
                        System.out.println("You equipped: " + armorToEquip.getName());
                        break;
                    } else if (choice == 2) {
                        System.out.println("You didn't equip the item");
                        break;
                    } else {
                        System.out.println("Invalid choice");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice not a number");
                }
            }
        }
    }
}





