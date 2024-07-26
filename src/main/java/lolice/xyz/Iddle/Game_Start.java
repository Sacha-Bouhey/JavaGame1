package lolice.xyz.Iddle;

import lolice.xyz.Location;
import lolice.xyz.Players.Characters;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Players.Player_choice;
import lolice.xyz.Players.Player_init;
import lolice.xyz.NPC.Quest;
import lolice.xyz.NPC.NPC;
import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game_Start {
    public static Map <String, NPC> NPC_init = new HashMap<>();
    public static Map <String, Quest> Quest_init = new HashMap<>();
    public static List<Location> Location_init = new ArrayList<>();
    public static Map <String, List<Enemy_init>> Enemies_init = new HashMap<>();
    public static void main(String[] args) {
        initEnemies();
        initNPC();
        initLocation();
        lore();
    }

    public static void initEnemies() {
        List<Skill> goblinSkills = new ArrayList<>();
        goblinSkills.add(new Skill("Goblin claw", "Weak attack, but dangerous for a beginner", 10, 0, true));
        List <Enemy_init> enemies = new ArrayList<>();
        enemies.add(new Enemy_init("Goblin", 100, 10, 1, 100, goblinSkills));
        Enemies_init.put("Forest", enemies);
    }

    public static void initLocation() {
        Location_init.add(new Location.Village("Stating village", 0, 0, true, "The starting village is a peaceful place for beginners to start their journey."));
        Location_init.add(new Location.Wilderness("Forest", 1, 0, false, "The forest is a dangerous place with monsters lurking around.", Enemies_init.get("Forest")));
        Location_init.add(new Location("Cave", 0, 1, false, "The cave is a dark and mysterious place with hidden treasures."));
        Location_init.add(new Location("Castle", 0, -1, false, "The castle is a place where the king resides and holds great power."));
    }

    public static void initNPC() {
        Quest_init.put("Tutorial 1",new Quest("Tutorial 1: Defeat Goblin","Defeat Goblin", 100, 10, "You must kill 2 goblins to complete this quest.", 2));

        NPC_init.put("Tutorial villager", new NPC("Tutorial villager", 100, 10, 1,1000, """
                Hello adventurer! \
                I have a quest for you. You must defeat 2 goblins to complete this quest.\s
                 You can find goblins in the forest. Good luck!\
                Oh, and also if you have some gold you can buy things in my shop !\s
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
        if (Location_init.getFirst() instanceof Location.Village) {
            ((Location.Village) Location_init.getFirst()).addNPC(NPC_init.get("Tutorial villager"));
        }
        Menu menu = new Menu(player);
        menu.showStartMenu();
    }
}