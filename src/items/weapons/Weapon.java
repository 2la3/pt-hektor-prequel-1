package items.weapons;

import items.Item;
import protagonist.MC;

public abstract class Weapon extends Item {

    int damage;
    int equipLevel;

    public int getDamage() { return damage; }

    public int getEquipLevel() { return equipLevel; }

    public void use() {
        MC mc = MC.getMC();
        mc.equipWeapon(this);
    }

}
