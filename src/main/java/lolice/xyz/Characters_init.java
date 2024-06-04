package lolice.xyz;
public class Characters_init{
    private int health;
    private int strength;
    private int mana;
    private int agility;
    private int defence;
    private int statpoint;

    public Characters_init(int Chealth, int Cstrength, int Cmana, int Cagility, int Cdefence, int Cstatpoint) {
        this.health = Chealth;
        this.strength = Cstrength;
        this.mana = Cmana;
        this.agility = Cagility;
        this.defence = Cdefence;
        this.statpoint = Cstatpoint;
    }

    //Getters
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
}

