package lolice.xyz;

public class Skill {
    public String SkillName;
    public String Description;
    public int Damage;
    public boolean IsActive;
    public int ManaCost;

    public Skill(String SkillName, String Description, int Damage, int ManaCost, boolean IsActive) {
        this.SkillName = SkillName;
        this.Description = Description;
        this.Damage = Damage;
        this.ManaCost = ManaCost;
        this.IsActive = IsActive;
    }

    //Getters
    public String getSkillName() {
        return SkillName;
    }

    public String getDescription() {
        return Description;
    }

    public int getDamage() {
        return Damage;
    }

    public int getManaCost() {
        return ManaCost;
    }

    public boolean isActive() {
        return IsActive;
    }

    //Setters
    public void setActive() {
        this.IsActive = true;
    }

    public static void useSkill(String SkillName) {
        System.out.println(SkillName + "is used");
    }
}
