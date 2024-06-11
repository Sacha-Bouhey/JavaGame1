package lolice.xyz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Characters {
    public static Map InitCharValue(String[] args) {
        Map<String, ArrayList<Integer>> default_class_value = new HashMap<>();

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

        return default_class_value;
    }
}
