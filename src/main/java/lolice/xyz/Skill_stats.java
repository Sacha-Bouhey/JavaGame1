package lolice.xyz;

public class Skill_stats {
    public String skill_name;
    public int skill_level;

    public Skill_stats(String skill_name, int skill_level) {
        this.skill_name = skill_name;
        this.skill_level = skill_level;
    }
    public String getSkill_name() {
        return skill_name;
    }
    public int getSkill_stats() {
        return skill_level;
    }

}

