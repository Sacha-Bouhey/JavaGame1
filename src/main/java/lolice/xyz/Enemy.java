package lolice.xyz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Enemy {

    public static Map<String, ArrayList<Integer>> InitEnemyValue() {
        Map<String, ArrayList<Integer>> default_enemy_value = new HashMap<>();

        default_enemy_value.put("Goblin", new ArrayList<>());
        default_enemy_value.get("Goblin").add(50);
        default_enemy_value.get("Goblin").add(6);
        default_enemy_value.get("Goblin").add(0);
        default_enemy_value.get("Goblin").add(1);

        return default_enemy_value;
    }
}