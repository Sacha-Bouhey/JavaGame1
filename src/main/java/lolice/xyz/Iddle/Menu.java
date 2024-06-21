package lolice.xyz.Iddle;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Skill;
import lolice.xyz.Skill_stats;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Characters_init player;

    public Menu(Characters_init player) {
        this.player = player;
    }

    public static void startMenu(Characters_init player) {
        while (true) {
            Scanner UserChoice = new Scanner(System.in);
            System.out.println("Welcome to lolo's RPG");
            System.out.println("1. Start game");
            System.out.println("2. Exit");
            System.out.println("Enter your choice: ");
            int Choice = UserChoice.nextInt();
            if (Choice == 1) {
                System.out.println("Game started");
                iddleMenu(player);
            } else if (Choice == 2) {
                System.out.println("Game exited, goodbye !");
                System.exit(0);
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private Skill_stats getSkill_statsByName(String skill_stats_name){
        for(Skill_stats skill_stats : player.getSkill_stats()) {
            if(skill_stats.getSkill_name().equals(skill_stats_name)) {
                return skill_stats;
            }
        }return null;
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
                startMenu(player);
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    }
    public void playerSkillStatsUpgrade() {
        while (true) {
            System.out.println("What do you want to upgrade?");
            Scanner UserChoice = new Scanner(System.in);
            System.out.println("1. Staff");
            System.out.println("2. Books");
            System.out.println("3. Magic gauntlet ");
            System.out.println("0. Return to main menu");
            System.out.println("Enter your choice: ");
            int Choice = UserChoice.nextInt();
            if (Choice == 1) {
                Skill_stats skill_stats = getSkill_statsByName("Staff");
                skill_stats.upgradeSkillStats(skill_stats, player);
                break;
            }
            else if(Choice == 2) {
                Skill_stats skill_stats = getSkill_statsByName("Books");
                skill_stats.upgradeSkillStats(skill_stats, player);
                break;
            } else if (Choice == 3) {
                Skill_stats skill_stats = getSkill_statsByName("Magic gauntlet");
                skill_stats.upgradeSkillStats(skill_stats, player);
                break;
            }
            else{
                System.out.println("Invalid choice");
                break;
            }
        }
    }
}
