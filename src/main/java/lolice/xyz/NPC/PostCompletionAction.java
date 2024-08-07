package lolice.xyz.NPC;

import lolice.xyz.Players.Characters_init;

import java.io.Serializable;

public interface PostCompletionAction extends Serializable {
     void execute(Characters_init player, NPC npc);
}