package lolice.xyz;

import lolice.xyz.NPC.NPC;

import java.util.List;

public class Location {
    public String name;
    public int x;
    public int y;
    private String description;
    public boolean unlocked;
    public List<NPC> npc;

    public Location(String name, int x, int y, boolean unlocked, String description) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.unlocked = unlocked;
        this.description = description;
    }

    //getters
    public String getLocationName() {
        return name;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    //setters
    public void setUnlocked(){
        this.unlocked = true;
    }

    public void setLocked(){
        this.unlocked = false;
    }

    public void showLocationInfo() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Unlocked: " + unlocked);
        for (NPC npc : npc) {
            npc.showNPCInfo();
        }
    }
}
