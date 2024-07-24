package lolice.xyz.Iddle;
import lolice.xyz.Battle;
import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.NPC.Quest;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Skill;
import lolice.xyz.Skill_stats;
import lolice.xyz.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Characters_init player;

    public Menu(Characters_init player) {
        this.player = player;
    }

    public void showStartMenu() {
        startMenu(this.player);
    }

    private void startMenu(Characters_init player) {
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

    private void iddleMenu(Characters_init player) {
        player.addActiveQuest(new Quest("Defeat 1 goblin","Defeat Goblin", 100, 10, "You must kill 10 goblins to complete this quest.", 1));
        Skill skill1 = new Skill("Goblin punch", "Strength", 10, 10, true);
        List <Skill> skillist = new ArrayList<Skill>();
        skillist.add(skill1);
        Enemy_init enemy = new Enemy_init("Goblin", 10, 10, 10, 10, skillist);
        Battle battle = new Battle(player, enemy);
        battle.Start();
        while (true) {
            System.out.println("What do you want to do?");
            Scanner UserChoice = new Scanner(System.in);
            System.out.println("1. Explore");
            System.out.println("2. Show current stats");
            System.out.println("3. Show current location");
            System.out.println("4. Show player inventory");
            System.out.println("5. Upgrade your weapon skills");
            System.out.println("6. Explore current location");
            System.out.println("0. Return to main menu");
            System.out.println("Enter your choice: ");
            int Choice = UserChoice.nextInt();
            if (Choice == 1) {
                //TODO: Move all this to a function inside the player class
                System.out.println("Which way do you want to explore ?");
                player.showAdjacentLocations(Game_Start.Location_init);
                System.out.println("Enter your choice: ");
                String Direction = UserChoice.next();

                if(Direction.equals("North")) {
                    //Check if unlocked parameter is equal to false
                    player.moveNorth();
                    if(!player.getCurrentLocation(Game_Start.Location_init).isUnlocked()) {
                        System.out.println("You can't go that way, the location is currently locked \n");
                        player.moveSouth();
                    }
                }
                else if(Direction.equals("South")) {
                    player.moveSouth();
                    if(!player.getCurrentLocation(Game_Start.Location_init).isUnlocked()) {
                        System.out.println("You can't go that way, the location is currently locked \n");
                        player.moveNorth();
                    }
                }
                else if(Direction.equals("East")) {
                    player.moveEast();
                    if(!player.getCurrentLocation(Game_Start.Location_init).isUnlocked()) {
                        System.out.println("You can't go that way, the location is currently locked \n");
                        player.moveWest();
                    }
                }
                else if(Direction.equals("West")) {
                    player.moveWest();
                    if(!player.getCurrentLocation(Game_Start.Location_init).isUnlocked()) {
                        System.out.println("You can't go that way, the location is currently locked \n");
                        player.moveEast();
                    }
                }
                else {
                    System.out.println("Invalid choice");
                }
                if(player.getCurrentLocation(Game_Start.Location_init).getLocationName().equals("Location not found")) {
                    System.out.println("You can't go that way, location does not exist. \n");
                }
            }
            else if (Choice == 2) {
                player.showInfo();
            }
            else if (Choice == 3) {
                System.out.println("Showing current location...");
                System.out.println("Current location is: "+ player.getCurrentLocation(Game_Start.Location_init).getLocationName() + "\n");
                player.showAdjacentLocations(Game_Start.Location_init);
                System.out.println("\n");
            }
            else if (Choice == 4) {
                System.out.println("Show current inventory");
            }
            else if (Choice == 5) {
                playerSkillStatsUpgrade();
            }
            else if (Choice == 6) {
                System.out.println("Exploring current location...");
                player.getCurrentLocation(Game_Start.Location_init).showLocationInfo();
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
            }
        }
    }
}
