package lolice.xyz.Players;

import lolice.xyz.Enemies.Enemy_init;

import java.io.Serial;
import java.io.Serializable;

public class Leveling implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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

    public static void gainExp(int exp, Characters_init player) {
        player.getLeveling().setExp(player.getLeveling().getExp()+exp);
        if (player.getLeveling().getExp() >= player.getLeveling().getExptolevel()) {
            LevelUp(player);
        }
    }

    public static void LevelUp(Characters_init player) {
        Leveling playerleveling = player.getLeveling();
        playerleveling.setLevel(playerleveling.getLevel() + 1);
        int currentXP = playerleveling.getExp() - playerleveling.getExptolevel();
        playerleveling.setExp(currentXP);
        playerleveling.setExptolevel((int) (playerleveling.getExptolevel() * 1.2));

        player.setStatpoint(player.getStatpoint() + 3);

        player.setMaxHealth(player.getHealth() + 10);
        player.setHealth(player.getMaxhealth());
        player.setMaxStrength(player.getMaxStrength() + 5);
        player.setMaxMana(player.getMaxMana() + 10);
        player.setAgility(player.getAgility() + 5);
        player.setDefence(player.getDefence() + 5);
    }

    public int getTotalExp() {
        int totalExp = 0;
        for(int i = 1; i < level; i++) {
            totalExp += (int) (exptolevel / 1.2);
        }
        return totalExp + exp;
    }
}
