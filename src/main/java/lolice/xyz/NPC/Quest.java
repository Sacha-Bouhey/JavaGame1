package lolice.xyz.NPC;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Players.Leveling;
import lolice.xyz.Items.Items;

public class Quest {
    private final String name;
    private int expReward;
    private int goldReward;
    //todo: add reward system.
    private final String dialog;
    private final String mission;
    private boolean completed;
    private int condition;
    private final int conditionGoal;
    private Items itemReward;

    public Quest(String name, String mission, int exp, int gold, String dialog, int conditionGoal) {
        this(name, mission, exp, gold, dialog, conditionGoal, null);
    }


    public Quest(String name, String mission, int exp, int gold, String dialog, int conditionGoal, Items itemReward) {
        this.name = name;
        this.expReward = exp;
        this.goldReward = gold;
        this.dialog = dialog;
        this.condition = 0;
        this.mission = mission;
        this.conditionGoal = conditionGoal;
        this.itemReward = itemReward;
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

    public int getConditionGoal() {
        return conditionGoal;
    }

    public Items getItemReward() {
        return itemReward;
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
        System.out.println("Dialog: " + mission);
        System.out.println("Exp: " + expReward);
        System.out.println("Gold: " + goldReward);
        if(itemReward != null) {
            System.out.println("Reward: " + itemReward.getName());
        }
    }

    public void showQuestDialog() {
        System.out.println("Name: " + name);
        System.out.println("Dialog: " + dialog);
        System.out.println("Condition: " + condition);
    }

    public void updateProgress(Characters_init player) {
        this.condition++;
        if (this.condition >= this.conditionGoal) {
            this.condition = this.conditionGoal;
            this.completed = true;
            checkQuestCompletion(player);
        }
    }

    public void checkQuestCompletion(Characters_init player) {
        if (this.completed) {
            System.out.println("Quest completed!");
            Leveling.gainExp(this.expReward, player);
            giveGoldReward(player);
            giveItemReward(player);
        } else {
            System.out.println("Quest not completed yet.");
        }
    }

    public void defeatEnemyCondition(Enemy_init enemy, Characters_init player) {
        if(!this.isCompleted() && this.mission.contains("Defeat "+enemy.getName())) {
            this.updateProgress(player);
        }
    }

    public void giveItemReward(Characters_init player) {
        if (this.completed && this.itemReward != null) {
            player.getInventory().addItem(this.itemReward);
        }
    }

    public void giveGoldReward(Characters_init player) {
        if (this.completed) {
            player.setGold(player.getGold() + this.goldReward);
        }
    }
}
