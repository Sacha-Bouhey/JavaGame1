package lolice.xyz.Iddle;
import lolice.xyz.Battle;
import lolice.xyz.Location;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Skill_stats;
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

    public void iddleMenu(Characters_init player) {
        while (true) {
            System.out.println("What do you want to do?");
            Scanner UserChoice = new Scanner(System.in);
            System.out.println("1. Explore");
            System.out.println("2. Show current stats");
            System.out.println("3. Show current location");
            System.out.println("4. Show player inventory");
            System.out.println("5. Show active quests");
            System.out.println("6. Upgrade your weapon skills");
            System.out.println("7. Explore current location");
            System.out.println("8. Talk to an NPC");
            System.out.println("0. Return to main menu");
            System.out.println("Enter your choice: ");
            int Choice = UserChoice.nextInt();



            // Player actions
            if (Choice == 1) {
                player.playerMove();
                iddleMenu(player);
            }
            else if (Choice == 2) {
                player.showInfo();
            }
            else if (Choice == 3) {
                System.out.println("Showing current location...");
                System.out.println("Your current location is: "+ player.getCurrentLocation().getLocationName() + "\n");
                player.showAdjacentLocations(player.getLocations());
                System.out.println("\n");
            }
            else if (Choice == 4) {
                System.out.println("Showing current inventory...");
                player.inventoryMenu();
            }
            else if (Choice == 5) {
                System.out.println("Showing active quests...");
                player.showQuestMenu();
            }
            else if (Choice == 6) {
                player.playerSkillStatsUpgrade();
            }
            else if (Choice == 7) {
                //TODO: move somewhere else
                System.out.println("If you are in the wilderness, you will encounter enemies. Are you sure you want to explore? (y/n)");
                while(true) {
                    try {
                        Scanner UserChoice2 = new Scanner(System.in);
                        String Choice2 = UserChoice2.nextLine();
                        if (Choice2.equals("y")) {
                            System.out.println("Exploring current location...");
                            player.getCurrentLocation().showLocationInfo();
                            if (player.getCurrentLocation() instanceof Location.Village) {
                                break;
                            } else if (player.getCurrentLocation() instanceof Location.Wilderness) {
                                Battle battle = new Battle(player, ((Location.Wilderness) player.getCurrentLocation()).selectRandomEnemy());
                                battle.Start();
                                break;
                            } else if (player.getCurrentLocation() instanceof Location.Dungeon) {
                                break;
                            }
                        } else {
                            System.out.println("Returning to main menu...");
                            iddleMenu(player);
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid choice");
                    }
                }
            }
            else if (Choice == 8) {
                player.talkToNPC();
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

}
