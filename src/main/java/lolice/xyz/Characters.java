package lolice.xyz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Characters {
    public static void InitChar(String[] args) {
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

        //Test if player input is working properly
        System.out.println(default_class_value.get("Mage").get(1));
        String UserChoice = Player_choice.GetClassName(args);
        System.out.println(UserChoice);
        //Initialisation for the player Character.
        Characters_init player = new Characters_init(
                default_class_value.get(UserChoice).get(0),
                default_class_value.get(UserChoice).get(1),
                default_class_value.get(UserChoice).get(2),
                default_class_value.get(UserChoice).get(3),
                default_class_value.get(UserChoice).get(4),
                default_class_value.get(UserChoice).get(5)
        );
        System.out.println(player.getAgility());

    }
}
