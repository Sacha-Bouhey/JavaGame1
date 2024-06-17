package lolice.xyz.Players;

import lolice.xyz.Enemies.Enemy_init;

public class Leveling {
    public int exp = 0;
    public int level = 1;
    public int exptolevel = 100;

    public Leveling(int exp, int level) {
        this.exp = exp;
        this.level = level;
        this.exptolevel = exptolevel;
    }


    //getters
    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public int getExptolevel() {
        return exptolevel;
    }


    //setters
    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setExptolevel(int exptolevel) {
        this.exptolevel = exptolevel;
    }

    public static void gainExp(Enemy_init enemy, Characters_init player) {
        if (enemy.getName().equals("Goblin")) {
            player.getLeveling().setExp(player.getLeveling().getExp()+10);
            System.out.println(player.getLeveling().getExp());
        }
        if (player.getLeveling().getExp() >= player.getLeveling().getExptolevel()) {
            LevelUp(player);
        }
    }

    public static void LevelUp(Characters_init player) {
        Leveling playerleveling = player.getLeveling();
        playerleveling.setLevel(playerleveling.getLevel() + 1);
        playerleveling.setExptolevel((int) (playerleveling.getExptolevel() * 1.2));
        playerleveling.setExp(0);

        player.setStatpoint(player.getStatpoint() + 3);

        player.setMaxHealth(player.getHealth() + 10);
        player.setStrength(player.getStrength() + 5);
        player.setMana(player.getMana() + 10);
        player.setAgility(player.getAgility() + 5);
        player.setDefence(player.getDefence() + 5);
    }
}
