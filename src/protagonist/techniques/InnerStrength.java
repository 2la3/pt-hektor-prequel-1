package protagonist.techniques;

import monsters.Monster;
import protagonist.MC;

public class InnerStrength extends Technique {

    public InnerStrength() {
        mana = 50;
        name = "Inner Strength";
        level = 1;
    }

    public boolean use(Monster m) {
        activateAbility();
        m.hpAdjust(this);
        return true;
    }

    @Override
    public void damageUpdate() { damage = 0; }

    @Override
    public boolean activateAbility() {
        if (MC.getMC().mp >= mana) {
            abilityActive = true;
            abilityToCycle = fightCycle + 10;
            MC.getMC().setStrength(MC.getMC().getStrength() + 50);
            return true;
        }
        else return false;
    }

    @Override
    public boolean unActivateAbility() {
        if (abilityActive) {
            MC.getMC().setStrength(MC.getMC().getStrength() - 50);
            abilityActive = false;
            return true;
        }
        return false;
    }

}
