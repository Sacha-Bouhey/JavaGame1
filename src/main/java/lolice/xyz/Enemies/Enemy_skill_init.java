package lolice.xyz.Enemies;

import lolice.xyz.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Enemy_skill_init {
    public static Enemy_init Enemies_skill_init(Map<String, ArrayList<Integer>> default_enemy_value, String EnemyName) {

        if (default_enemy_value.containsKey(EnemyName)) {
            ArrayList<Integer> EnemyStats = default_enemy_value.get(EnemyName);

            List<Skill> GoblinSkills = new ArrayList<Skill>();
            GoblinSkills.add(new Skill("Weak attack", "A pathetic attack", 5, 0, true));
            GoblinSkills.add(new Skill("Claw attack", "Can hurt people", 6, 0, true));

            return new Enemy_init(EnemyName, EnemyStats.get(0), EnemyStats.get(1), EnemyStats.get(2), EnemyStats.get(3), GoblinSkills);
        }


        return null;
    }
}
