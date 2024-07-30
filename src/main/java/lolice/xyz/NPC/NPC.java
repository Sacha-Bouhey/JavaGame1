package lolice.xyz.NPC;

import lolice.xyz.Items.Inventory;
import lolice.xyz.Players.Characters_init;

import java.util.ArrayList;
import java.util.List;

public class NPC {
    private final String name;
    private int hp;
    private int damage;
    private int gold;
    private String dialog;
    private List<Quest> quests;
    private Inventory.Shop shop;

    public NPC(String name, int hp, int damage, int level, int gold, String dialog) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.gold = gold;
        this.dialog = dialog;
        this.shop = new Inventory.Shop(20);
        this.quests = new ArrayList<>();
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

    public List<Quest> getQuest() {
        return quests;
    }

    public Inventory.Shop getShop() {
        return shop;
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
        System.out.println("Damage: " + damage + "\n");
    }

    public void showNPCQuestName() {
        for (Quest quest : quests) {
            System.out.println(quest.getName());
        }
    }

    public void showNPCQuestInfo() {
        for (Quest quest : quests) {
            quest.showQuestInfo();
        }
    }

    public void addQuest(Quest quest) {
        this.quests.add(quest);
    }

    public void removeQuest(Quest quest) {
        this.quests.remove(quest);
    }

    public void questAccepted(Characters_init player, Quest quest) {
        System.out.println("Quest accepted: " + quest.getName());
        quest.setOrigin(this.getName());
        player.addActiveQuest(quest);
        questAcceptUnlockable(quest,player);
        removeQuest(quest);
    }

    public void questAcceptUnlockable(Quest quest,Characters_init player) {
        if (quest.getName().equals("Tutorial 1: Defeat Goblin")) {
            System.out.println("You just unlocked the forest location! \n You can go there with the explore option to find and defeat goblins." +
                    " \n You might find something usefull there! Good luck! \n");
            player.unlockLocation("Forest");
        }
    }
}
