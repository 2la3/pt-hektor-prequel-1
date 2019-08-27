package protagonist.techniques;

import monsters.Monster;
import protagonist.MC;

import java.util.Observable;

public class RegularHit extends Technique {

    public RegularHit() {
        mana = 0;
        name = "Regular Hit";
    }

    public boolean use(Monster m) {
        MC.getMC().mp = MC.getMC().mp - mana;
        m.hpAdjust(this);
        return true;
    }

    public void damageUpdate() {
        damage = MC.getMC().getAttackTotal();
    }

    @Override
    public boolean activateAbility() {
        return false;
    }

    @Override
    public boolean unActivateAbility() {
        return false;
    }

}
