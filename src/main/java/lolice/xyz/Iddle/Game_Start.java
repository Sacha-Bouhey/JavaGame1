package lolice.xyz.Iddle;

import lolice.xyz.Players.Player_choice;

public class Game_Start {
    public static void lore() {
        System.out.println("You woke up in a field, and you have a mission. You know it, but you can't remember...");
        System.out.println("You just remember something ! You were a: ");
        String Choice = Player_choice.GetClassName();
        System.out.println("Yes ! you were a: " + Choice + "! You can finally start your journey");
    }
}
