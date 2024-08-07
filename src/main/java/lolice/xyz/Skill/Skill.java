package lolice.xyz.Skill;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Players.Characters_init;

import java.io.Serial;
import java.util.List;
import java.io.Serializable;

public class Skill implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String SkillName;
    private final String Description;
    private final int Damage;
    private boolean IsActive;
    private final int ManaCost;
    private final List<Effect> Effect;


    public Skill(String SkillName, String Description, int Damage, int ManaCost, boolean IsActive, List<Effect> Effect) {
        this.SkillName = SkillName;
        this.Description = Description;
        this.Damage = Damage;
        this.ManaCost = ManaCost;
        this.IsActive = IsActive;
        this.Effect = Effect;
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

    public List<Effect> getEffect() {
        return Effect;
    }

    //Setters
    public void setActive() {
        this.IsActive = true;
    }

    public static void useSkill(String SkillName) {
        System.out.println(SkillName + "is used");
    }

    //Other
    public void showInfo() {
        System.out.println("Skill name: " + SkillName);
        System.out.println("Description: " + Description);
        System.out.println("Damage: " + Damage);
        System.out.println("Mana cost: " + ManaCost);
        System.out.println("Is active: " + IsActive);
    }

    public String getName() {
        return SkillName;
    }

    public void applyEffects(Characters_init player, Enemy_init enemy) {
        for (Effect effect : Effect) {
            Effect newEffect = new Effect(effect.getEffectName(), effect.getDescription(), effect.getDamage(), effect.getDuration(), effect.getType());
            newEffect.applyEffect(player, enemy);
        }
    }
}
