package lolice.xyz.NPC;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Players.Leveling;
import lolice.xyz.Items.Items;


import java.util.ArrayList;
import java.util.List;


public class Quest {
    public static Object QuestType;
    private final String name;
    private int expReward;
    private int goldReward;
    private final String dialog;
    private final String mission;
    private boolean completed;
    private int condition;
    private final int conditionGoal;
    private Items itemReward;
    private String origin;
    private Quest nextQuest;
    private QuestType type;
    private boolean isReturned;
    private List<PostCompletionAction> postCompletionActionList;

    public Quest(String name, String mission, int exp, int gold, String dialog, int conditionGoal, QuestType type) {
        this(name, mission, exp, gold, dialog, conditionGoal, null, type);
    }


    public Quest(String name, String mission, int exp, int gold, String dialog, int conditionGoal, Items itemReward, QuestType type) {
        this.name = name;
        this.expReward = exp;
        this.goldReward = gold;
        this.dialog = dialog;
        this.condition = 0;
        this.mission = mission;
        this.conditionGoal = conditionGoal;
        this.itemReward = itemReward;
        this.nextQuest = null;
        this.type = type;
        this.isReturned = false;
        this.postCompletionActionList = new ArrayList<>();
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

    public String getOrigin() {
        return origin;
    }

    public QuestType getType() {
        return type;
    }

    public Quest getNextQuest() {
        return nextQuest;
    }

    public boolean isReturned() {
        return isReturned;
    }

    //setters
    public void setExp(int exp) {
        this.expReward = exp;
    }

    public void setGold(int gold) {
        this.goldReward = gold;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setNextQuest(Quest nextQuest) {
        this.nextQuest = nextQuest;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public void addPostCompletionAction(PostCompletionAction action) {
        postCompletionActionList.add(action);
    }

    public void postCompletionActions(Characters_init player, NPC npc) {
        for (PostCompletionAction action : postCompletionActionList) {
            action.execute(player, npc);
        }
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

    public void updateProgress() {
        this.condition++;
        if (this.condition >= this.conditionGoal) {
            this.condition = this.conditionGoal;
            this.completed = true;
        }
    }

    public void checkQuestCompletion(Characters_init player, NPC npc) {
        if (this.completed) {
            System.out.println("Quest completed!");
            Leveling.gainExp(this.expReward, player);
            giveGoldReward(player);
            giveItemReward(player);
            this.postCompletionActions(player, npc);
            if (this.nextQuest != null) {
                npc.addQuest(this.nextQuest);
                System.out.println("New quest available, talk to " + npc.getName() + " to start it.");
                player.addActiveQuest(this.nextQuest);
            }
        } else {
            System.out.println("Quest not completed yet.");
        }
    }

    public void defeatEnemyCondition(Enemy_init enemy, Characters_init player) {
        if(enemy == null) {
            return;
        }
        if(!this.isCompleted() && this.mission.contains("Defeat "+enemy.getName())) {
            this.updateProgress();
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
    public void buyItemCondition(Items item, Characters_init player) {
        if(this.getMission().contains(item.getName())) {
            this.updateProgress();
        }
        else if(this.getMission().contains("Buy any")) {
            this.updateProgress();
        }
    }

    public void talkToCondition(NPC npc, Characters_init player) {
        if(this.getMission().contains(npc.getName())) {
            this.updateProgress();
        }
    }
}
