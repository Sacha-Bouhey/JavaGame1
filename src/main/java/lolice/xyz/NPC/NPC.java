package lolice.xyz.NPC;

import lolice.xyz.Players.Characters_init;

public class NPC {
    private final String name;
    private int hp;
    private int damage;
    private int gold;
    private String dialog;
    private Quest quest;

    public NPC(String name, int hp, int damage, int level, int gold, String dialog) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.gold = gold;
        this.dialog = dialog;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public int getGold() {
        return gold;
    }

    public String getDialog() {
        return dialog;
    }

    public Quest getQuest() {
        return quest;
    }

    //setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void showNPCInfo() {
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp);
        System.out.println("Damage: " + damage);
        System.out.println("Gold: " + gold + "\n");
    }

    public void showNPCQuest() {
        quest.showQuestInfo();
    }

    public void addQuest(Quest quest) {
        this.quest = quest;
    }

    public void questAccepted(Characters_init player) {
        System.out.println("Quest accepted: " + quest.getName());
        player.addActiveQuest(quest);
        questUnlockable(quest,player);
    }

    public void questUnlockable(Quest quest,Characters_init player) {
        if (quest.getName().equals("Tutorial 1: Defeat Goblin")) {
            System.out.println("You just unlocked the forest location! \n You can go there with the explore option to find and defeat goblins." +
                    " \n You might find something usefull there! Good luck! \n");
            player.unlockLocation("Forest");
        }
    }
}
