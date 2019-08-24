package protagonist;

import items.food.Food;
import items.weapons.Weapon;
import map.Map;

public class MC {
    private static final int STRENGTH = 50;
    private static final int INTEL = 10;
    private static final int VITALITY = 20;

    private static MC mc = null;
    private Inventory inv = new Inventory();

    private int strength;
    private int intel;
    private int vitality;

    private int maxHP;
    private int maxMP;
    private int maxEnergy;
    private int maxExp;

    private int attackMin;
    private int attackMax;
    private int defenseMin;
    private int defenseMax;
    private int attackRate;
    private int defenseRate;

    public int hp;
    public int mp;
    public int energy;
    public int exp;

    private Weapon equipped;

    private MC() {
        this.strength = STRENGTH;
        this.intel = INTEL;
        this.vitality = VITALITY;

        this.maxHP = 5 * this.vitality + 1 * this.strength;
        this.maxMP = 5 * this.intel;
        this.maxEnergy = 100;
        this.maxExp = 1000;

        this.attackMin = this.strength/5;
        this.attackMax =  this.strength/4;
        this.defenseMin = 2 * this.strength + this.vitality;
        this.defenseMax = 4 * this.strength + 2* this.vitality;
        this.attackRate = 150 * this.intel;
        this.defenseRate = 100 * this.intel;

        this.hp = this.maxHP;
        this.mp = this.maxMP;
        this.energy = this.maxEnergy;
        this.exp = 0;

        this.equipped = null;


    }

    // REQUIRES :: hp is positive, zero or negative integer
    // MODIFIES :: this
    // EFFECTS :: adjusts the hp of this
    public void hpAdjustment(int hp) {
        if (this.hp + hp <= 0) {
            // IMPLEMENT THIS HERE
        }
        else this.hp = Math.min(this.hp + hp, maxHP);
    }

    // MODIFIES :: this
    // EFFECTS :: equips the specified weapon, removes it from the inventory, adjusts damage, and unequips any weapon previously equipped and adds it to inventory
    public void equipWeapon(Weapon weapon) {
        Weapon current = equipped;
        if (inv.inventoryRemove(weapon)) {
            equipped = weapon;
            if (current == null) {
                attackMin = attackMin + equipped.getDamage();
                attackMax = attackMax + equipped.getDamage();
            }
            else {
                attackMin = attackMin - current.getDamage() + equipped.getDamage();
                attackMax = attackMax - current.getDamage() + equipped.getDamage();
                inv.inventoryAdd(current);
            }
        }
    }

    // MODIFIES :: this
    // EFFECTS :: removes equipped weapon, places it into the inventory, adjusts damage and returns true if space is available, else return false
    public boolean unequipWeapon(Weapon weapon) {
        if (equipped == null) return false;
        if (inv.inventoryAdd(equipped)) {
            equipped = null;
            return true;
        }
        return false;
    }

    public boolean useFood(Food food) {
        if (inv.inventoryRemove(food)) {
            food.use();
            return true;
        }
        return false;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntel() {
        return intel;
    }

    public int getVitality() {
        return vitality;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public Inventory getInventory() {
        return inv;
    }

    public int getMaxHP() { return maxHP; }

    public static MC getMC() {
        if (mc == null)
            mc = new MC();
        return mc;
    }
}
