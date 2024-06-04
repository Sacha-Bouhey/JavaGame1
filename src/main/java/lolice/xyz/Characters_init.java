package lolice.xyz;
public class Characters_init{
    private int endurance;
    private int force;
    private int mana;
    private int agilite;
    private int defense;
    private int statpoint;

    public Characters_init(int Cendurance, int Cforce, int Cmana, int Cagilite, int Cdefense, int Cstatpoint) {
        this.endurance = Cendurance;
        this.force = Cforce;
        this.mana = Cmana;
        this.agilite = Cagilite;
        this.defense = Cdefense;
        this.statpoint = Cstatpoint;
    }
    public int getMana() {
        return mana;
    }

    public int getForce() {
        return force;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getAgilite() {
        return agilite;
    }

    public int getDefense() {
        return defense;
    }

    public int getStatpoint() {
        return statpoint;
    }

    // Setters
    public void setMana(int newMana) {
        this.mana = newMana;
    }

    public void setForce(int newForce) {
        this.force = newForce;
    }

    public void setEndurance(int newEndurance) {
        this.endurance = newEndurance;
    }

    public void setAgilite(int newAgilite) {
        this.agilite = newAgilite;
    }

    public void setDefense(int newDefense) {
        this.defense = newDefense;
    }

    public void setStatpoint(int newStatpoint) {
        this.statpoint = newStatpoint;
    }
}

