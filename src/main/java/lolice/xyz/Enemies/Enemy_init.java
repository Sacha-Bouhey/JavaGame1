
package lolice.xyz.Enemies;

import lolice.xyz.Skill;

import java.util.List;

public class Enemy_init {
    private String name;
    private int health;
    private int strength;
    private int mana;
    private int defence;
    private List<Skill> skills;

    public Enemy_init(String Ename, int Ehealth, int Estrength, int Emana, int Edefence, List<Skill> Eskills) {
        this.name = Ename;
        this.health = Ehealth;
        this.strength = Estrength;
        this.mana = Emana;
        this.defence = Edefence;
        this.skills = Eskills;
    }

    public Enemy_init(Enemy_init enemy) {
        this.name = enemy.name;
        this.health = enemy.health;
        this.strength = enemy.strength;
        this.mana = enemy.mana;
        this.defence = enemy.defence;
        this.skills = enemy.skills; // Assuming skills are immutable or shared
    }

    //Getters
    public String getName() {
        return name;
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
    public int getDefence() {
        return defence;
    }
    public List<Skill> getSkills() {
        return skills;
    }

    //Setters
    public void setHealth(int newhealth) {
        this.health = newhealth;
    }
    public void setStrength(int newstrength) {
        this.strength = newstrength;
    }
    public void setMana(int newmana) {
        this.mana = newmana;
    }
    public void setDefence(int newdefence) {
        this.defence = newdefence;
    }
    public void takeDamage(int damage) {
        this.health -= damage - defence;
    }
    //showInfo
    public void showInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Health: " + this.health);
        System.out.println("Strength: " + this.strength);
        System.out.println("Mana: " + this.mana);
        System.out.println("Defence: " + this.defence);
        System.out.println("Skills: ");
        for (Skill skill : this.skills) {
            System.out.println(skill.getSkillName());
        }
    }

    public int useSkill(Skill enemy_skill) {
        int damage = enemy_skill.getDamage();
        if(enemy_skill.getManaCost() == 0) {
            return damage + strength;
        } else {
            this.mana -= enemy_skill.getManaCost();
            return enemy_skill.getDamage();
        }
    }
}