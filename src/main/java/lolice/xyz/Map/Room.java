package lolice.xyz.Map;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Items.Chest;
import lolice.xyz.Items.Items;
import lolice.xyz.NPC.NPC;

import java.util.List;

public class Room {
    private String name;
    private int x;
    private int y;
    private boolean cleared;
    public Room(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.cleared = false;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared() {
        this.cleared = true;
    }

    public static class TreasureRoom extends Room {
        private final Chest chest;
        private final List<NPC> npcs;

        public TreasureRoom(String name, int x, int y, Chest chest, List<NPC> npcs) {
            super(name, x, y);
            this.chest = chest;
            this.npcs = npcs;
        }

        public Chest getChest() {
            return chest;
        }

        public List<NPC> getNpcs() {
            return npcs;
        }

    }

    public static class EnemyRoom extends Room {
        private final List<Enemy_init> enemies;

        public EnemyRoom(String name, int x, int y, List<Enemy_init> enemies) {
            super(name, x, y);
            this.enemies = enemies;
        }

        public List<Enemy_init> getEnemies() {
            return enemies;
        }

    }

    public static class BossRoom extends Room {
        private final List<Enemy_init> enemies;

        public BossRoom(String name, int x, int y, List<Enemy_init> enemies) {
            super(name, x, y);
            this.enemies = enemies;
        }

        public List<Enemy_init> getEnemies() {
            return enemies;
        }
    }
}

