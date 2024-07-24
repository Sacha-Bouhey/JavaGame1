package lolice.xyz.NPC;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Players.Characters;
import lolice.xyz.Players.Characters_init;

public class Quest {
    private final String name;
    private int expReward;
    private int goldReward;
    //todo: add reward system.
    private final String dialog;
    private final String mission;
    private boolean completed;
    private int condition;
    private int conditionGoal;

    public Quest(String name, String mission, int exp, int gold, String dialog, int conditionGoal) {
        this.name = name;
        this.expReward = exp;
        this.goldReward = gold;
        this.dialog = dialog;
        this.condition = 0;
        this.mission = mission;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getExp() {
        return expReward;
    }

    public int getGold() {
        return goldReward;
    }

    public String getDialog() {
        return dialog;
    }

    public String getMission() {
        return mission;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getCondition() {
        return condition;
    }

    //setters
    public void setExp(int exp) {
        this.expReward = exp;
    }

    public void setGold(int gold) {
        this.goldReward = gold;
    }

    public void showQuestInfo() {
        System.out.println("Name: " + name);
        System.out.println("Exp: " + expReward);
        System.out.println("Gold: " + goldReward);
    }

    public void showQuestDialog() {
        System.out.println("Name: " + name);
        System.out.println("Dialog: " + dialog);
        System.out.println("Condition: " + condition);
    }

    public void showQuestReward() {
        System.out.println("Name: " + name);
        System.out.println("Exp: " + expReward);
        System.out.println("Gold: " + goldReward);
    }

    public void updateProgress(Characters_init player) {
        this.condition++;
        if (this.condition >= this.conditionGoal) {
            this.completed = true;
            checkQuestCompletion(player);
        }
    }

    public void checkQuestCompletion(Characters_init player) {
        if (this.completed) {
            System.out.println("Quest completed!");
            player.getLeveling().gainExp(this.expReward, player);
        } else {
            System.out.println("Quest not completed yet.");
        }
    }

    public void defeatEnemyCondition(Enemy_init enemy, Characters_init player) {
        if(!this.isCompleted() && this.mission.contains("Defeat "+enemy.getName())) {
            this.updateProgress(player);
        }
    }
}
