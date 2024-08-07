package lolice.xyz.Map;

import lolice.xyz.Players.Characters_init;
import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.NPC.NPC;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        private List<Enemy_init> enemies;

        public LocationWithEnemies(String name, int x, int y, boolean unlocked, String description, List<Enemy_init> enemies) {
            super(name, x, y, unlocked, description);
            this.enemies = enemies;
        }

        public List<Enemy_init> getEnemies() {
            return this.enemies;
        }

        public Enemy_init selectRandomEnemy(Characters_init player) {
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


        public class Dungeon extends Location {
            private final List<Enemy_init> enemies;
            private boolean cleared;
            private final int rooms;

            public Dungeon(String name, int x, int y, boolean unlocked, String description, List<Enemy_init> enemies, int rooms) {
                super(name, x, y, unlocked, description);
                this.enemies = enemies;
                this.cleared = false;
                this.rooms = rooms;
            }

            public List<Enemy_init> getEnemies() {
                return this.enemies;
            }

            public boolean isCleared() {
                return cleared;
            }

            public int getRooms() {
                return rooms;
            }

            public void setCleared() {
                this.cleared = true;
            }

            public void generateDungeon() {
                for(int i = 0; i < rooms; i++) {

                }
            }

            public Room.TreasureRoom generateTreasureRoom(){
                return null;
            }

            public Room.EnemyRoom generateEnemyRoom(Characters_init player){
                List<Enemy_init> enemiesForRoom = new ArrayList<>();
                enemiesForRoom.add(selectRandomEnemy(player));

                Room.EnemyRoom enemyRoom = new Room.EnemyRoom("Enemy Room", 0, 0, enemies);

                return null;
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
