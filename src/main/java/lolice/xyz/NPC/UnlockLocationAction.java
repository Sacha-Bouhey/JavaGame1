package lolice.xyz.NPC;

import lolice.xyz.Players.Characters_init;
import lolice.xyz.Items.Items;

import java.io.Serial;
import java.io.Serializable;

public class UnlockLocationAction implements PostCompletionAction, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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

