package lolice.xyz.NPC;

public class NPC {
    private final String name;
    private int hp;
    private int damage;
    private int gold;
    private String dialog;

    public NPC(String name, int hp, int damage, int level, int exp, int gold, String dialog) {
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
        System.out.println("Gold: " + gold);
    }
}
