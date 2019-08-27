package monsters;

import protagonist.techniques.Technique;

public class Beaver extends Monster {

    public Beaver() {
        name = "Beaver";
        hp = 400;
        damage = 15;
        hitChance = 100;
        coinsDrop = 200;
        experienceReward = 300;
    }

    @Override
    public boolean hpAdjust(Technique technique) {
        damageDoneTo = technique.getDamage() * 3;
        hp = hp - damageDoneTo;
        if (hp <= 0) {
            alive = false;
            return false;
        }
        return true;
    }

}
