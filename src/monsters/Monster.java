package monsters;

import items.Item;
import protagonist.MC;
import protagonist.techniques.Technique;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Monster implements Observer {

    public int hp;

    public String name;
    boolean alive = true;
    int damage;
    int damageDealt;
    int damageDoneTo = 0;
    int hitChance;
    int coinsDrop;
    int experienceReward;
    List<Item> dropTable = new ArrayList<>();

    // EFFECTS :: return true if monster is still alive after the hit (i.e. hp > 0) else set alive to false and return false;
    public boolean hpAdjust(Technique technique) {
        damageDoneTo = technique.getDamage();
        hp = hp - damageDoneTo;
        if (hp <= 0) {
            alive = false;
            return false;
        }
        return true;
    }

    public int getDamageDoneTo() { return damageDoneTo; }

    public boolean getAlive() { return alive; }

    // EFFECTS :: returns the damage of the next hit
    public int hit() {
        damageDealt = damage;
        return damage;
    }

    public int getDamageDealt() { return damageDealt; }

    public String getName() { return name; }

    // EFFECTS :: updates MC.exp and MC.inv.coins with the reward amount from winning the fight. Returns the potential loot items.
    public List<Item> getLoot() {
        List<Item> loot = dropTable;
        for (Item i : dropTable) {
            int randomNum = ThreadLocalRandom.current().nextInt(0,100);
            if (randomNum > 21) dropTable.remove(i);
        }
        MC.getMC().getInventory().coinsModifier(coinsDrop);
        MC.getMC().experienceGain(experienceReward);
        return loot;
    }

    @Override
    public void update(Observable o, Object obj) {
    }

}
