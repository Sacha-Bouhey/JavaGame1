package lolice.xyz;

import java.util.ArrayList;
import java.util.List;

public class Characters_init{
    private String name;
    private int health;
    private int strength;
    private int mana;
    private int agility;
    private int defence;
    private int statpoint;
    private List<Skill> skills;

    public Characters_init(String Cname, int Chealth, int Cstrength, int Cmana, int Cagility, int Cdefence, int Cstatpoint,List<Skill> skills) {
        this.name = Cname;
        this.health = Chealth;
        this.strength = Cstrength;
        this.mana = Cmana;
        this.agility = Cagility;
        this.defence = Cdefence;
        this.statpoint = Cstatpoint;
        this.skills = skills;
    }

    //Getters
    public String getName(){
        return this.name;
    }
    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getMana() {
        return mana;
    }

    public int getAgility() {
        return agility;
    }

    public int getDefence() {
        return defence;
    }

    public int getStatpoint() {
        return statpoint;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    // Setters
    public void sethealth(int newhealth) {
        this.health = newhealth;
    }

    public void setStrength(int newStrength) {
        this.strength = newStrength;
    }

    public void setMana(int newMana) {
        this.mana = newMana;
    }

    public void setAgility(int newAgility) {
        this.agility = newAgility;
    }

    public void setDefence(int newDefence) {
        this.defence = newDefence;
    }

    public void setStatpoint(int newStatpoint) {
        this.statpoint = newStatpoint;
    }

    public void TakeDamage(int damage) {
        health -= damage - defence;
    }
}

