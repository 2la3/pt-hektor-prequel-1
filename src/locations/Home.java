package locations;

import protagonist.MC;

public class Home {

    // REQUIRES :: location on Map is at home; i.e. location id == 0.
    // MODIFIES :: MC
    // EFFECTS  :: restores energy to 100
    public static void rest() {
        MC mc = MC.getMC();
        mc.energy = 100;
    }

    // REQUIRES :: location on Map is at home; i.e. location id == 0.
    // MODIFIES :: MC
    // EFFECTS  :: restores hp to 1/3 of max
    public static void eatSimpleMeal() {
        MC mc = MC.getMC();
        mc.hp = (mc.getMaxHP()/3);
    }

    // MODIFIES :: MC, MC.inv
    // EFFECTS  :: if funds are available, make the appropriate adjustment, restore hp to max and return true; otherwise return false
    public static boolean eatExpensiveMeal() {
        MC mc = MC.getMC();
        if (mc.getInventory().coinsModifier(-100)) {
            mc.hp = mc.getMaxHP();
            return true;
        }
        return false;
    }
}
