package lolice.xyz.Map;

import lolice.xyz.Battle;
import lolice.xyz.Items.GameObjects;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.NPC.NPC;
import lolice.xyz.Items.Chest;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Location implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private int x;
    private int y;
    private String description;
    private boolean unlocked;

    public Location(String name, int x, int y, boolean unlocked, String description) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.unlocked = unlocked;
        this.description = description;
    }

    //getters
    public String getLocationName() {
        return name;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public String getDescription() {
        return description;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void showLocationInfo() {
        System.out.println("Name: " + getLocationName());
        System.out.println("Description: " + getDescription() + "\n");}

    //setters
    public void setUnlocked(){
        this.unlocked = true;
    }

    public void setLocked(){
        this.unlocked = false;
    }

    public static class LocationWithEnemies extends Location {
        private static List<Enemy_init> enemies;

        public LocationWithEnemies(String name, int x, int y, boolean unlocked, String description, List<Enemy_init> enemies) {
            super(name, x, y, unlocked, description);
            this.enemies = enemies;
        }

        public List<Enemy_init> getEnemies() {
            return this.enemies;
        }

        public static Enemy_init selectRandomEnemy(Characters_init player) {
            int randomIndex = (int) (Math.random() * enemies.size());
            Enemy_init selectedEnemy = new Enemy_init(enemies.get(randomIndex));
            int enemyLevel = (int) (Math.random() * (player.getLeveling().getLevel() + 2)) + 1;
            selectedEnemy.getLeveling().setLevel(enemyLevel);
            return selectedEnemy;
        }

        public static class Wilderness extends LocationWithEnemies {
            public Wilderness(String name, int x, int y, boolean unlocked, String description, List<Enemy_init> enemies) {
                super(name, x, y, unlocked, description, enemies);
            }
        }


        public static class Dungeon extends Location {
            private final List<Enemy_init> enemies;
            private boolean cleared;
            private final int numberOfRooms;
            private List<Room> rooms;

            public Dungeon(String name, int x, int y, boolean unlocked, String description, List<Enemy_init> enemies, int rooms) {
                super(name, x, y, unlocked, description);
                this.enemies = enemies;
                this.cleared = false;
                this.numberOfRooms = rooms;
            }

            public List<Enemy_init> getEnemies() {
                return this.enemies;
            }

            public boolean isCleared() {
                return cleared;
            }

            public int getNumberOfRooms() {
                return numberOfRooms;
            }

            public void setCleared() {
                this.cleared = true;
            }

            public void generateDungeon(Characters_init player) {
                Set<String> usedCoordinates = new HashSet<>();
                rooms = new ArrayList<>();

                // Add the first room as an empty room
                int x = 0;
                int y = 0;
                rooms.add(new Room("Empty Room", x, y));
                usedCoordinates.add(x + "," + y);

                for (int i = 1; i < numberOfRooms; i++) {
                    int a = new Random().nextInt(10);
                    if (a < 8) {
                        rooms.add(generateEnemyRoom(player, 3, x, y));
                    } else {
                        rooms.add(generateTreasureRoom(x, y));
                    }

                    // Generate new coordinates
                    boolean validCoordinates = false;
                    while (!validCoordinates) {
                        int direction = new Random().nextInt(4);
                        int newX = x;
                        int newY = y;
                        switch (direction) {
                            case 0: newX = x + 1; break; // Right
                            case 1: newX = x - 1; break; // Left
                            case 2: newY = y + 1; break; // Up
                            case 3: newY = y - 1; break; // Down
                        }
                        String newCoordinates = newX + "," + newY;
                        if (!usedCoordinates.contains(newCoordinates)) {
                            x = newX;
                            y = newY;
                            usedCoordinates.add(newCoordinates);
                            validCoordinates = true;
                        }
                    }
                }
                rooms.add(generateBossRoom(x+1, y));
            }

            public Room.BossRoom generateBossRoom(int x, int y){
                return new Room.BossRoom("Boss Room", x, y, null);
            }
            public Room.TreasureRoom generateTreasureRoom(int x, int y){
                Chest chest;
                int a = new Random().nextInt(10);
                if(a < 4) {
                    chest = GameObjects.SMALL_CHEST;
                }
                else if(a < 8) {
                    chest = GameObjects.WOODEN_CHEST;
                }
                else {
                    chest = GameObjects.BIG_CHEST;
                }

                chest.generateItems();
                return new Room.TreasureRoom("Treasure Room", x, y, chest, null);
            }

            public Room.EnemyRoom generateEnemyRoom(Characters_init player, int numberOfEnemies, int x, int y){
                List<Enemy_init> enemiesForRoom = new ArrayList<>();
                for(int i = 0; i < numberOfEnemies; i++) {
                    enemiesForRoom.add(selectRandomEnemy(player));
                }
                return new Room.EnemyRoom("Enemy Room", x, y, enemiesForRoom);
            }

            public Room getCurrentRoom(int x, int y) {
                for (Room room : rooms) {
                    if (room.getX() == x && room.getY() == y) {
                        return room;
                    }
                }
                return null;
            }

            public void exploreDungeon(Characters_init player) {
                player.setX(0);
                player.setY(0);
                if(getCurrentRoom(player.getX(), player.getX()) == null) {
                    System.out.println("error in dungeon. Leaving");
                    return;
                }
                else {
                    while(!this.cleared) {
                        if(!getCurrentRoom(player.getX(), player.getY()).isCleared()){
                            Room currentRoom = getCurrentRoom(player.getX(), player.getY());
                            System.out.println("You entered a room: " + currentRoom.getName());
                            if(currentRoom instanceof Room.EnemyRoom) {
                                System.out.println("You encountered enemies in this room");
                                List<Enemy_init> enemies = ((Room.EnemyRoom) currentRoom).getEnemies();
                                Battle battle = new Battle(player, enemies);
                                battle.Start();
                                if(player.getHealth() <= 0) {
                                    System.out.println("You died. Returning to village");
                                    return;
                                }
                                else {
                                    currentRoom.setCleared();
                                }
                            }
                            else if(currentRoom instanceof Room.TreasureRoom) {
                                System.out.println("You found a treasure room");
                                Chest chest = ((Room.TreasureRoom) currentRoom).getChest();
                                chest.openChest(player);
                                currentRoom.setCleared();
                            }
                            else if(currentRoom instanceof Room.BossRoom) {
                                System.out.println("You found the boss room");
                                List<Enemy_init> enemies = new ArrayList<>();
                                enemies.add(selectRandomEnemy(player));
                                Battle battle = new Battle(player, enemies);
                                battle.Start();
                                if(player.getHealth() <= 0) {
                                    System.out.println("You died. Returning to village");
                                    return;
                                }
                                else {
                                    currentRoom.setCleared();
                                    this.cleared = true;
                                }
                            }
                        }
                        else {
                            System.out.println("You already cleared this room");

                        }
                    }
                }


            }
        }
    }



    public static class Village extends Location {
        private List<NPC> npc;
        public Village(String name, int x, int y, boolean unlocked, String description) {
            super(name, x, y, unlocked, description);
            this.npc = new ArrayList<>();
        }

        public List<NPC> getNPC() {
            return npc;
        }

        @Override
        public void showLocationInfo() {
            System.out.println("Name: " + getLocationName());
            System.out.println("Description: " + getDescription());
            System.out.println("Unlocked: " + isUnlocked() + "\n");
            if(npc != null) {
                for (NPC npc : npc) {
                    System.out.println("NPCs in this location: ");
                    npc.showNPCInfo();
                }
            }
        }

        public void showNPCInfo() {
            for(NPC npc : npc) {
                npc.showNPCInfo();
            }
        }

        public void addNPC(NPC npc) {
            this.npc.add(npc);
        }
    }
}
