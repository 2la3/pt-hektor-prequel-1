package npc;

import items.Item;
import protagonist.Inventory;
import protagonist.MC;

public abstract class Shopkeeper extends NPC {

    // MODIFIES :: MC.inv
    // EFFECTS  :: adds the item to MC's inventory if MC can afford it and has inventory space, return true; if not, return false
    public static boolean purchaseItem(Item item) {
        Inventory mc = MC.getMC().getInventory();
        if (mc.enoughCoins(0 - item.getCost())) {
            if (mc.inventoryAdd(item)) {
                mc.coinsModifier(0 - item.getCost());
                return true;
            }
        }
        return false;
    }
}
