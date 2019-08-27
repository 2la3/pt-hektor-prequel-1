package monsters;

import protagonist.MC;

import java.util.Observable;

public class WaspNest extends Monster {

    public WaspNest() {
        name = "Wasp Nest";
        hp = 200;
        damage = 1;
        hitChance = 100;
        coinsDrop = 50;
        experienceReward = 125;
    }

    @Override
    public int hit() {
        // WaspNest damage subject to MC.defenceTotal
        int damage = this.damage * (100 - MC.getMC().getDefenseTotal())/100;
        damageDealt = damage;
        return damage;
    }

    // WaspNest Ability :: damages increases every hit
    @Override
    public void update(Observable o, Object obj) {
        if (obj.equals("true")) {
            damage = damage + 7;
        }
    }
}
