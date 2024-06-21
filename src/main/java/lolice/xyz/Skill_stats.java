package lolice.xyz;

import lolice.xyz.Players.Characters_init;

public class Skill_stats {
    private String skill_name;
    private String class_name;
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

    public void upgradeSkillStats(Skill_stats skill_stats, Characters_init player) {
        if(player.getStatpoint() > 0) {
            player.setStatpoint(player.getStatpoint() -1);
            skill_stats.setSkill_stats(skill_stats.getSkill_level() + 1);
            System.out.println("Skill upgraded !");
        }
        else {
            System.out.println("Not enough stat points");
        }
    }

}

