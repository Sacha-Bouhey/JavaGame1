package lolice.xyz;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Skills {
    public static void SkillsInit(String[] args) {
        Map<String, ArrayList> skills = new HashMap<>();
        skills.put("MageSkill" , new ArrayList<>());
        skills.get("MageSkill").add("test");
        skills.get("MageSkill").add(1);
    }
}
