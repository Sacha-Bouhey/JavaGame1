package lolice.xyz.Iddle;

import lolice.xyz.Location;
import lolice.xyz.Players.Characters;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Players.Player_choice;
import lolice.xyz.Players.Player_init;

import java.util.HashMap;
import java.util.Map;


public class Game_Start {
    public static Map <String, Location> Location_init = new HashMap<>();
    public static void main(String[] args) {
        initLocation();
        lore();
    }
    public static void initLocation() {
        Location_init = new HashMap<>();
        Location_init.put("Starting village", new Location("Stating village", 0, 0, true, "The starting village is a peaceful place for beginners to start their journey."));
        Location_init.put("Forest", new Location("Forest", 1, 0, false, "The forest is a dangerous place with monsters lurking around."));
        Location_init.put("Cave", new Location("Cave", 0, 1, false, "The cave is a dark and mysterious place with hidden treasures."));
        Location_init.put("Castle", new Location("Castle", 0, -1, false, "The castle is a place where the king resides and holds great power."));
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
        Menu menu = new Menu(player);
        menu.showStartMenu();
    }
}