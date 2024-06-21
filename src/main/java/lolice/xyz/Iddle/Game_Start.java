package lolice.xyz.Iddle;

import lolice.xyz.Players.Characters;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Players.Player_choice;
import lolice.xyz.Players.Player_init;

import java.util.ArrayList;

public class Game_Start {
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
        Menu.startMenu(player);
    }
}
