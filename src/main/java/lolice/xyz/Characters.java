package lolice.xyz;

import com.sun.jdi.LocalVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Characters {
    public static void InitCharValue(String[] args) {
        Map<String, ArrayList<Integer>> default_class_value = new HashMap<String, ArrayList<Integer>>();

        //Init Mage class
        default_class_value.put("Mage", new ArrayList<Integer>());
        default_class_value.get("Mage").add(100);
        default_class_value.get("Mage").add(5);
        default_class_value.get("Mage").add(200);
        default_class_value.get("Mage").add(10);
        default_class_value.get("Mage").add(1);
        default_class_value.get("Mage").add(0);

        //Init Warrior class
        default_class_value.put("Warrior", new ArrayList<Integer>());
        default_class_value.get("Warrior").add(200);
        default_class_value.get("Warrior").add(20);
        default_class_value.get("Warrior").add(100);
        default_class_value.get("Warrior").add(5);
        default_class_value.get("Warrior").add(10);
        default_class_value.get("Warrior").add(0);

        //Init Rogue class
        default_class_value.put("Rogue", new ArrayList<Integer>());
        default_class_value.get("Rogue").add(110);
        default_class_value.get("Rogue").add(25);
        default_class_value.get("Rogue").add(120);
        default_class_value.get("Rogue").add(20);
        default_class_value.get("Rogue").add(2);
        default_class_value.get("Rogue").add(0);

        //Init Magic SwordMan class
        default_class_value.put("Magic SwordMan", new ArrayList<Integer>());
        default_class_value.get("Magic SwordMan").add(130);
        default_class_value.get("Magic SwordMan").add(30);
        default_class_value.get("Magic SwordMan").add(150);
        default_class_value.get("Magic SwordMan").add(10);
        default_class_value.get("Magic SwordMan").add(5);
        default_class_value.get("Magic SwordMan").add(0);


        String ClassName = Player_choice.GetClassName(args);
        ArrayList<Integer> PlayerSkill = default_class_value.get(ClassName);
        //Initialisation for the player Character.
        if (default_class_value.containsKey(ClassName)) {
            List<Skill> MageSkills = new ArrayList<>();
            MageSkills.add(new Skill("Fireball", "Une boule de feu globalement", 50, 20, false));
            MageSkills.add(new Skill("TestBall", "C'est rien tkt", 9999999, 0, true));

            List<Skill> WarriorSkills = new ArrayList<>();
            WarriorSkills.add(new Skill("Berserker Stance", "WOW !", 10, 0, true));

            Characters_init player;
            if(ClassName.equals("Mage")) {
                player = new Characters_init(PlayerSkill.get(0), PlayerSkill.get(1), PlayerSkill.get(2), PlayerSkill.get(3), PlayerSkill.get(4), PlayerSkill.get(5), MageSkills);
            }
            else if(ClassName.equals("Warrior")) {
                player = new Characters_init(PlayerSkill.get(0), PlayerSkill.get(1), PlayerSkill.get(2), PlayerSkill.get(3), PlayerSkill.get(4), PlayerSkill.get(5), MageSkills);
            }
            else {
                player = new Characters_init(PlayerSkill.get(0), PlayerSkill.get(1), PlayerSkill.get(2), PlayerSkill.get(3), PlayerSkill.get(4), PlayerSkill.get(5), new ArrayList<>());
            }
            System.out.println(player.getAgility());
        }
    }
}
