package lolice.xyz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enemy {
    public static Map InitEnemyValue(String[] args) {
        Map<String, ArrayList<Integer>> default_enemy_value = new HashMap<>();

        default_enemy_value.put("Gobelin", new ArrayList<>());
        default_enemy_value.get("Gobelin").add(50);
        default_enemy_value.get("Gobelin").add(6);
        default_enemy_value.get("Gobelin").add(0);
        default_enemy_value.get("Gobelin").add(1);

        return default_enemy_value;
    }
}