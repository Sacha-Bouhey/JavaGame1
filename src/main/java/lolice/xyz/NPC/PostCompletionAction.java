package lolice.xyz.NPC;

import lolice.xyz.Players.Characters_init;
import lolice.xyz.NPC.NPC;

public interface PostCompletionAction {
     void execute(Characters_init player, NPC npc);
}