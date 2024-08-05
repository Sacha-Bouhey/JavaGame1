package lolice.xyz.NPC;

import lolice.xyz.Players.Characters_init;
import lolice.xyz.Items.Items;

public class UnlockLocationAction implements PostCompletionAction {
    private final String location;

    public UnlockLocationAction(String location) {
        this.location = location;
    }

    @Override
    public void execute(Characters_init player, NPC npc) {
        System.out.println("You have unlocked the location: " + location + "!");
        player.unlockLocation(location);
    }
}

