package items.food;

import items.Item;
import protagonist.MC;

public abstract class Food extends Item {

    int heal;

    // MODIFIES :: mc
    // EFFECTS  :: consumes the food object and changes the hp of mc
    public void use() {
        MC mc = MC.getMC();
        mc.hpAdjustment(heal);
    }

    public int getHeal() { return heal; }

}
