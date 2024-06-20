package lolice.xyz.Iddle;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Skill_stats;

import java.util.Scanner;

public class Menu {
    public static void startMenu() {
        while (true) {
            Scanner UserChoice = new Scanner(System.in);
            System.out.println("Welcome to lolo's RPG");
            System.out.println("1. Start game");
            System.out.println("2. Exit");
            System.out.println("Enter your choice: ");
            int Choice = UserChoice.nextInt();
            if (Choice == 1) {
                System.out.println("Game started");
                return;
            } else if (Choice == 2) {
                System.out.println("Game exited, goodbye !");
                System.exit(0);
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
    public static void iddleMenu(Characters_init player) {
        while (true) {
            System.out.println("What do you want to do?");
            Scanner UserChoice = new Scanner(System.in);
            System.out.println("1. Explore");
            System.out.println("2. Show current stats");
            System.out.println("3. Show current location");
            System.out.println("4. Show player inventory");
            System.out.println("0. Return to main menu");
            System.out.println("Enter your choice: ");
            int Choice = UserChoice.nextInt();
            if (Choice == 1) {
                System.out.println("Explore");
            }
            else if (Choice == 2) {
                player.showInfo();
            }
            else if (Choice == 3) {
                System.out.println("Show current location");
            }
            else if (Choice == 4) {
                System.out.println("Show current inventory");
            }
            else if (Choice == 0) {
                System.out.println("Returning to main menu...");
                startMenu();
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    }
    public static void playerSkillStatsUpgrade(Skill_stats skill_stats) {
        while (true) {
            System.out.println("What do you want to upgrade?");
            Scanner UserChoice = new Scanner(System.in);
            System.out.println("1. Staff");
            System.out.println("2. Books");
            System.out.println("3. Magic gauntlet ");
        }
    }
}
