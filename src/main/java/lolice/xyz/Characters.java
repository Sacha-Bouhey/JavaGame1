package lolice.xyz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Characters {
    public static void InitChar(String[] args) {
        Map<String, ArrayList<Integer>> default_class_value = new HashMap<String, ArrayList<Integer>>();


        default_class_value.put("Mage", new ArrayList<Integer>());
        default_class_value.get("Mage").add(100);
        default_class_value.get("Mage").add(5);
        default_class_value.get("Mage").add(200);
        default_class_value.get("Mage").add(10);
        default_class_value.get("Mage").add(1);
        default_class_value.get("Mage").add(0);

        System.out.println(default_class_value.get("Mage").get(1));

    }

}
