package lolice.xyz;

import java.util.List;

public class Enemy {
    private int Health;
    private int strength;
    private int mana;
    private int defence;
    private List<Skill> skills;

    public Enemy(int Ehealth, int Estrength, int Emana, int Edefence, List<Skill> Eskills) {
        this.Health = Ehealth;
        this.strength = Estrength;
        this.mana = Emana;
        this.defence = Edefence;
        this.skills = Eskills;
    }
    //Getters
    public int getHealth() {
        return Health;
    }
    public int getStrength() {
        return strength;
    }
    public int getMana() {
        return mana;
    }
    public int getDefence() {
        return defence;
    }
    public List<Skill> getSkills() {
        return skills;
    }

    //Setters
}
