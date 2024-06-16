package lolice.xyz;

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
//
//    public int getStrength() {
//        return strength;
//    }
//
//    public int getMana() {
//        return mana;
//    }
//
//    public int getAgility() {
//        return agility;
//    }
//
//    public int getDefence() {
//        return defence;
//    }
//
//    public int getStatpoint() {
//        return statpoint;
//    }

    public List<Skill> getSkills() {
        return skills;
    }

    // Setters
//    public void setHealth(int newHealth) {
//        this.health = newHealth;
//    }
//
//    public void setStrength(int newStrength) {
//        this.strength = newStrength;
//    }
//
//    public void setMana(int newMana) {
//        this.mana = newMana;
//    }
//
//    public void setAgility(int newAgility) {
//        this.agility = newAgility;
//    }
//
//    public void setDefence(int newDefence) {
//        this.defence = newDefence;
//    }
//
//    public void setStatpoint(int newStatpoint) {
//        this.statpoint = newStatpoint;
//    }
//
//    public void TakeDamage(int damage) {
//        health -= damage - defence;
//    }

    //Show info
    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Strength: " + strength);
        System.out.println("Mana: " + mana);
        System.out.println("Agility: " + agility);
        System.out.println("Defence: " + defence);
        System.out.println("Statpoint: " + statpoint);
    }

    //Check if enough mana
    public boolean checkMana(int manaCost) {
        return mana >= manaCost;
    }

    //Use skill
    public int useSkill(Skill player_skill) {
        //Use skill
        int damage = player_skill.getDamage();
        //If skill doesn't cost mana, add strength to damage
        if(player_skill.getManaCost() == 0)
            damage += this.strength;
        //If skill costs mana, remove mana
        else {
            this.mana -= player_skill.getManaCost();
        }
        return damage;
    }
}

