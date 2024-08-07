package lolice.xyz.Iddle;

import lolice.xyz.Items.GameObjects;
import lolice.xyz.Items.Items;
import lolice.xyz.Location;
import lolice.xyz.Players.Characters;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Players.Player_choice;
import lolice.xyz.Players.Player_init;
import lolice.xyz.NPC.*;
import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Skill.Skill;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;


public class Game_Start {
    public static Map <String, NPC> NPC_init = new HashMap<>();
    public static Map <String, Quest> Quest_init = new HashMap<>();
    public static List<Location> Location_init = new ArrayList<>();
    public static Map <String, List<Enemy_init>> Enemies_init = new HashMap<>();
    public static void main(String[] args) {
        Characters_init player = loadPlayer();
        if (player == null) {
            initEnemies();
            initNPC();
            initLocation();
            lore();
        } else {
            Menu menu = new Menu(player);
            menu.showStartMenu();
        }
    }

    private static Characters_init loadPlayer(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"))){
            System.out.println("Loading save file automatically");
            return (Characters_init) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public static void initEnemies() {
        List<Skill> goblinSkills = new ArrayList<>();
        goblinSkills.add(new Skill("Goblin claw", "Weak attack, but dangerous for a beginner", 10, 0, true, null));
        List <Enemy_init> enemies = new ArrayList<>();
        enemies.add(new Enemy_init("Goblin", 100, 10, 100, 1, goblinSkills));
        Enemies_init.put("Forest", enemies);
    }

    public static void initLocation() {
        Location_init.add(new Location.Village("Stating village", 0, 0, true, "The starting village is a peaceful place for beginners to start their journey."));
        Location_init.add(new Location.Wilderness("Forest", 1, 0, false, "The forest is a dangerous place with monsters lurking around.", Enemies_init.get("Forest")));
        Location_init.add(new Location("Cave", 0, 1, false, "The cave is a dark and mysterious place with hidden treasures."));
        Location_init.add(new Location("Castle", 0, -1, false, "The castle is a place where the king resides and holds great power."));


        if (Location_init.getFirst() instanceof Location.Village) {
            ((Location.Village) Location_init.getFirst()).addNPC(NPC_init.get("Tutorial villager"));
            ((Location.Village) Location_init.getFirst()).addNPC(NPC_init.get("Anderson"));
        }
    }

    public static void initNPC() {
        Quest_init.put("Tutorial 1", new Quest("Tutorial 1: Talk to: Anderson", "Talk to Anderson", 10, 10, "You must talk to Anderson to complete this quest.", 1, QuestType.TALK));

        Quest_init.get("Tutorial 1").addPostCompletionAction(new UnlockLocationAction("Forest"));

        Quest_init.put("Tutorial 2", new Quest("Tutorial 2: Defeat Goblin","Defeat Goblin", 100, 10, "You must kill 2 goblins to complete this quest. You can find goblins in the forest.", 2, new Items.Weapon.mageWeapon("Wooden staff", "A basic staff for beginners", 10, 25, false, 2, 100, 1, 1, 10, 100), QuestType.KILL));

        Quest_init.get("Tutorial 2").addPostCompletionAction(new AddItemToShopAction(GameObjects.SMALL_HEALING_POTION));

        Quest_init.get("Tutorial 2").setNextQuest(new Quest("Tutorial 3: Buy something in the shop","Buy any", 100, 100, "Buy any item in the shop to complete this quest", 1, QuestType.BUY));

        Quest_init.get("Tutorial 1").setNextQuest(Quest_init.get("Tutorial 2"));


        NPC_init.put("Tutorial villager", new NPC("Tutorial villager", 100, 10, 1,1000, """
                Hello adventurer! \
                If you're new here, I have some quests for you !\s
                They will help you during your journey !\
                Oh, and also if you have some gold you can buy things in my shop !\s
                """));

        NPC_init.put("Anderson", new NPC("Anderson", 100, 10, 1, 1000, """
                Hello adventurer! \
                You must be the one Tutorial villager told me about. \
                Go talk to him again, he has another quest for you.\s
                """));



        NPC_init.get("Tutorial villager").addQuest(Quest_init.get("Tutorial 1"));
    }

    public static void lore() {
        System.out.println("You woke up in a field, and you have a mission. You know it, but you can't remember...");
        System.out.println("You just remember something ! You were a: ");

        Characters_init player = null;
        while (player == null) {
            System.out.println("Available classes");
            for (String className : Characters.InitCharValue().keySet()) {
                System.out.println(className);
            }
            String Choice = Player_choice.GetClassName();
            player = Player_init.Player_init(Characters.InitCharValue(), Choice);
            if(player == null) {
                System.out.println("Invalid class!");
            }
        }
        System.out.println("Yes ! you were a: " + player.getName() + "! You can finally start your journey");
        System.out.println("Good luck !");
        player.setLocations(Location_init);
        Menu menu = new Menu(player);
        System.out.println("Do you want to read the tutorial? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String tutorial = scanner.nextLine();
        if (tutorial.equals("y")) {
            System.out.println("Tutorial:");
            System.out.println("You can explore the world by moving to different locations.");
            scanner.nextLine();
            System.out.println("You can unlock new locations by completing the storyline quests.");
            scanner.nextLine();
            System.out.println("You can talk to an NPC by selecting the appropriate option.");
            scanner.nextLine();
            System.out.println("When you talk to an NPC, you can accept quests or enter the shop to buy items or sell items.");
            scanner.nextLine();
            System.out.println("Quests are important as they give gold, exp point and sometimes Items to help you during your journey.");
            scanner.nextLine();
            System.out.println("You can only fight in non-village locations, so make sure you are prepared before exploring.");
            scanner.nextLine();
            System.out.println("Managing inventory is very important, if you finish a quest without enough space in your inventory, you will lose the reward.");
            scanner.nextLine();
            System.out.println("You can sell items you dont need for extra gold, this will also help you to manage your inventory.");
            scanner.nextLine();
            System.out.println("You have multiple way to gain gold, you can sell items, complete quests or defeat enemies (Not all enemy drop gold).");
            scanner.nextLine();
            System.out.println("You can also use skills to help you defeat enemies. There are multiple ways to gain skills");
            scanner.nextLine();
            System.out.println("First is the most obvious, you can gain skills by leveling up.");
            scanner.nextLine();
            System.out.println("Second is by buying them in a shop.");
            scanner.nextLine();
            System.out.println("Third is by completing quests (You may find secret quests to access a new secret class).");
            scanner.nextLine();
            System.out.println("Fourth is by upgrading your weapon stats, if you upgrade one enough it will give you access to exclusive skill for your class.");
            scanner.nextLine();
            System.out.println("Good luck on your journey! (Press enter to continue)");
        }
        menu.showStartMenu();
    }
}