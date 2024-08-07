package lolice.xyz.Iddle;
import lolice.xyz.Battle;
import lolice.xyz.Map.Location;
import lolice.xyz.Players.Characters_init;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    private final Characters_init player;
    private final Scanner userChoiceScanner;

    public Menu(Characters_init player) {
        this.player = player;
        this.userChoiceScanner = new Scanner(System.in); // Initialize the scanner once
    }

    public void showStartMenu() {
        startMenu(this.player);
    }

    private void startMenu(Characters_init player) {
        while (true) {
            System.out.println("Welcome to lolo's RPG");
            System.out.println("1. Start game");
            System.out.println("2. Exit");
            System.out.println("Enter your choice: ");
            int Choice = userChoiceScanner.nextInt();
            if (Choice == 1) {
                System.out.println("Game started");
                iddleMenu(player);
            } else if (Choice == 2) {
                System.out.println("Game exited, goodbye !");
                return;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public void iddleMenu(Characters_init player) {
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. Explore");
            System.out.println("2. Show current stats");
            System.out.println("3. Show current location");
            System.out.println("4. Show player inventory");
            System.out.println("5. Show active quests");
            System.out.println("6. Upgrade your weapon skills");
            System.out.println("7. Explore current location");
            System.out.println("8. Talk to an NPC");
            System.out.println("9. Save game");
            System.out.println("0. Return to main menu");
            System.out.println("Enter your choice: ");
            int Choice = userChoiceScanner.nextInt();
            userChoiceScanner.nextLine(); // Consume the newline character

            System.out.println("Choice entered: " + Choice);

            // Player actions
            if (Choice == 1) {
                player.playerMove();
                iddleMenu(player);
            } else if (Choice == 2) {
                player.showInfo();
            } else if (Choice == 3) {
                System.out.println("Showing current location...");
                System.out.println("Your current location is: " + player.getCurrentLocation().getLocationName() + "\n");
                player.showAdjacentLocations(player.getLocations());
                System.out.println("\n");
            } else if (Choice == 4) {
                System.out.println("Showing current inventory...");
                player.inventoryMenu();
            } else if (Choice == 5) {
                System.out.println("Showing active quests...");
                player.showQuestMenu();
            } else if (Choice == 6) {
                player.playerSkillStatsUpgrade();
            } else if (Choice == 7) {
                while (true) {
                    try {
                        System.out.println("If you are in the wilderness, you will encounter enemies. Are you sure you want to explore? (y/n)");
                        String choice = userChoiceScanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            System.out.println("Exploring current location...");
                            player.getCurrentLocation().showLocationInfo();
                            if (player.getCurrentLocation() instanceof Location.Village) {
                                break;
                            } else if (player.getCurrentLocation() instanceof Location.LocationWithEnemies.Wilderness) {
                                Battle battle = new Battle(player, ((Location.LocationWithEnemies.Wilderness) player.getCurrentLocation()).selectRandomEnemy(player));
                                battle.Start();
                                break;
                            } else if (player.getCurrentLocation() instanceof Location.LocationWithEnemies.Dungeon) {
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
                        userChoiceScanner.nextLine(); // Clear the buffer
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                }
            } else if (Choice == 8) {
                player.talkToNPC();
            } else if (Choice == 9) {
                savePlayer(player);
            } else if (Choice == 0) {
                System.out.println("Returning to main menu...");
                return;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private void savePlayer(Characters_init player){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"))){
            oos.writeObject(player);
            System.out.println("Player data saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving player data" + e.getMessage());
        }
    }
}
