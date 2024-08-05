package lolice.xyz.NPC;

import lolice.xyz.Items.Items;
import lolice.xyz.Players.Characters_init;

public class AddItemToShopAction implements PostCompletionAction {
    private final Items item;

    public AddItemToShopAction(Items item) {
        this.item = item;
    }

    @Override
    public void execute(Characters_init player, NPC npc) {
        npc.getShop().addItem(item);
    }
}
