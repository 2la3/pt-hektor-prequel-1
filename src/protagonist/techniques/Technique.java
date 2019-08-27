package protagonist.techniques;

import monsters.Monster;

import java.util.Observable;
import java.util.Observer;

public abstract class Technique implements Observer {

    String name;
    int damage;
    int mana;
    public boolean fightActive = false;
    public boolean abilityActive = false;
    public int fightCycle = 0;
    public int abilityToCycle = 0;

    public abstract boolean use(Monster m);
    public String getName() { return name; }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("true")) {
            fightActive = true;
            fightCycle++;
            checkAbility();
            damageUpdate();
            }
        else {
            fightActive = false;
            fightCycle = 0;
            abilityToCycle = 0;
            unActivateAbility();
        }
    }

    // MODIFIES :: this
    // EFFECTS :: checks to see if the ability should remain active, if not undo the ability's effects
    void checkAbility() {
        if (abilityActive) {
            if (fightCycle > abilityToCycle) {
                unActivateAbility();
            }
        }
    }

    public abstract boolean activateAbility();
    public abstract boolean unActivateAbility();
    public abstract void damageUpdate();

    public int getDamage() { return damage; }

    public int getMana() { return mana; }

}
