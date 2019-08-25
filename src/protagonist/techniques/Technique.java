package protagonist.techniques;

import java.util.Observable;
import java.util.Observer;

public abstract class Technique implements Observer {

    String name;
    int mana;
    public boolean fightActive = false;
    public boolean abilityActive = false;
    public int fightCycle = 0;
    public int abilityToCycle = 0;

    public abstract void use();
    public String getName() { return name; }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("true")) {
            fightActive = true;
            fightCycle++;
            if (abilityActive) {
                checkAbility();
            }
        }
        else {
            fightActive = false;
            fightCycle = 0;
            abilityToCycle = 0;
        }
    }

    // EFFECTS :: checks to see if the ability should remain active, if not undo the ability's effects
    private void checkAbility() {

    }

}
