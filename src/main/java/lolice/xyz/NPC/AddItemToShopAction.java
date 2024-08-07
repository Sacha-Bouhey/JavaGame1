package lolice.xyz.NPC;

import lolice.xyz.Items.Items;
import lolice.xyz.Players.Characters_init;

import java.io.Serial;
import java.io.Serializable;

public class AddItemToShopAction implements PostCompletionAction, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Items item;


    public AddItemToShopAction(Items item) {
        this.item = item;
    }

    @Override
    public void execute(Characters_init player, NPC npc) {
        npc.getShop().addItem(item);
    }
}
