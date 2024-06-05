package lolice.xyz;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Skills {
    public String SkillName;
    public String Description;
    public int Damage;
    public boolean IsActive;

    public Skills(String SkillName, String Description, int Damage, boolean IsActive) {
        this.SkillName = SkillName;
        this.Description = Description;
        this.Damage = Damage;
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

    public boolean isActive() {
        return IsActive;
    }

    //Setters

    public void setActive() {
        this.IsActive = true;
    }

    public void useSkill(String SkillName) {

    }
}
