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
        default_class_value.put("Mage", new ArrayList<Integer>(Arrays.asList(100, 5, 200, 10, 1, 0)));

        //Init Warrior class
        default_class_value.put("Warrior", new ArrayList<Integer>(Arrays.asList(200, 20, 100, 5, 10, 0)));

        //Init Rogue class
        default_class_value.put("Rogue", new ArrayList<Integer>(Arrays.asList(110, 25, 120, 20, 2, 0)));

        //Init Magic SwordMan class
        default_class_value.put("Magic SwordMan", new ArrayList<Integer>(Arrays.asList(130, 30, 150, 10, 5, 0)));

        return default_class_value;
    }
}
