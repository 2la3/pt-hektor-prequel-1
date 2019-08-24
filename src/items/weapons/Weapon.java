package items.weapons;

import items.Item;
import protagonist.MC;

public abstract class Weapon extends Item {

    int damage;

    public int getDamage() { return damage; }

    public void use() {
        MC mc = MC.getMC();
        mc.equipWeapon(this);
    }

}
