package lolice.xyz;

public class Location {
    public String name;
    public int x;
    public int y;
    public boolean unlocked;

    public Location(String name, int x, int y, boolean unlocked) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.unlocked = unlocked;
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

}
