package lolice.xyz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player_init {
    public static Characters_init Player_init(Map<String, ArrayList<Integer>> default_class_value, String ClassName) {

        if (default_class_value.containsKey(ClassName)) {
            ArrayList<Integer> PlayerStats = default_class_value.get(ClassName);

            List<Skill> MageSkills = new ArrayList<>();
            MageSkills.add(new Skill("Fireball", "Une boule de feu globalement", 50, 20, false));
            MageSkills.add(new Skill("TestBall", "C'est rien tkt", 9999999, 0, true));

            List<Skill> WarriorSkills = new ArrayList<>();
            WarriorSkills.add(new Skill("Berserker Stance", "WOW !", 10, 0, true));

            Characters_init player;
            if(ClassName.equals("Mage")) {
                return new Characters_init("Mage", PlayerStats.get(0), PlayerStats.get(1), PlayerStats.get(2), PlayerStats.get(3), PlayerStats.get(4), PlayerStats.get(5), MageSkills);
            }
            else if(ClassName.equals("Warrior")) {
                return new Characters_init("Warrior", PlayerStats.get(0), PlayerStats.get(1), PlayerStats.get(2), PlayerStats.get(3), PlayerStats.get(4), PlayerStats.get(5), WarriorSkills);
            }
        }
            return null;
    }
}
