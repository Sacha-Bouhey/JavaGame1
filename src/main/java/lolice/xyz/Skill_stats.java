package lolice.xyz;


import java.io.Serial;
import java.io.Serializable;

public class Skill_stats implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String skill_name;
    private final String class_name;
    private int skill_level;

    public Skill_stats(String skill_name, String class_name, int skill_level) {
        this.skill_name = skill_name;
        this.class_name = class_name;
        this.skill_level = 0;
    }

    public String getSkill_name() {
        return skill_name;
    }
    public String getClass_name() {
        return class_name;
    }
    public int getSkill_level() {
        return skill_level;
    }
    public void setSkill_stats(int new_skill_level) {
        this.skill_level = new_skill_level;
    }

    public void showSkillStats() {
        System.out.println("Skill name: " + this.skill_name + " Skill level: " + this.skill_level);
    }
}

