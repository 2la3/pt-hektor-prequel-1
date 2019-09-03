package protagonist;

import items.food.Food;
import items.weapons.Weapon;
import npc.Ambulance;
import protagonist.techniques.RegularHit;
import protagonist.techniques.Technique;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MC extends Observable implements Observer {
    private static final int STRENGTH = 50;
    private static final int INTEL = 10;
    private static final int VITALITY = 20;

    private static MC mc = null;
    private Inventory inv = new Inventory();
    public List<Technique> techniques = new ArrayList<>();

    private int strength;
    private int intel;
    private int vitality;

    private int maxHP;
    private int maxMP;
    private int maxEnergy;
    private int maxExp;

    private int attackBase;
    private int attackTotal;
    private int defenseBase;
    private int defenseTotal;
    private int attackRate;
    private int defenseRate;

    public int hp;
    public int mp;
    public int energy;
    public int exp;
    public int level;

    private Weapon equipped;

    private MC() {
        this.strength = STRENGTH;
        this.intel = INTEL;
        this.vitality = VITALITY;

        this.maxHP = 5 * this.vitality + this.strength;
        this.maxMP = 5 * this.intel;
        this.maxEnergy = 100;
        this.maxExp = 1000;

        this.attackBase = this.strength/4;
        this.attackTotal =  this.strength/4;
        this.defenseBase = this.strength/5 + this.vitality/5;
        this.defenseTotal = this.strength/5 + this.vitality/5;
        this.attackRate = 150 * this.intel;
        this.defenseRate = 100 * this.intel;

        this.hp = this.maxHP;
        this.mp = this.maxMP;
        this.energy = this.maxEnergy;
        this.exp = 0;
        this.level = 1;

        this.equipped = null;
        Technique reg = new RegularHit();
        techniques.add(reg);
        addObserver(reg);
    }

    // REQUIRES :: hp is positive, zero or negative integer
    // MODIFIES :: this
    // EFFECTS :: adjusts the hp of this, if after adjustment, this.hp < 0 return false, else return true
    public boolean hpAdjustment(int hp) {
        if (this.hp + hp <= 0) {
            this.hp = this.hp + hp;
            Ambulance.serveHP();
            return false;
        }
        else this.hp = Math.min(this.hp + hp, maxHP);
        return true;
    }

    // MODIFIES :: this
    // EFFECTS :: equips the specified weapon, removes it from the inventory, adjusts damage, and unequips any weapon previously equipped and adds it to inventory if
    //            level requirement is met.
    public boolean equipWeapon(Weapon weapon) {
        if (weapon.getEquipLevel() > level) return false;
        Weapon current = equipped;
        if (inv.inventoryRemove(weapon)) {
            equipped = weapon;
            if (current == null) {
                attackBase = attackBase + equipped.getDamage();
                attackTotal = attackTotal + equipped.getDamage();
            }
            else {
                attackBase = attackBase - current.getDamage() + equipped.getDamage();
                attackTotal = attackTotal - current.getDamage() + equipped.getDamage();
                inv.inventoryAdd(current);
            }
            return true;
        }
        return false;
    }

    // MODIFIES :: this
    // EFFECTS :: removes equipped weapon, places it into the inventory, adjusts damage and returns true if space is available, else return false
    public boolean unequipWeapon() {
        if (equipped == null) return false;
        if (inv.inventoryAdd(equipped)) {
            attackBase = attackBase - equipped.getDamage();
            attackTotal = attackTotal - equipped.getDamage();
            equipped = null;
            return true;
        }
        return false;
    }

    // MODIFIES :: MC.inv, this.hp
    // EFFECTS :: attempts to use food item, if successful, modifies this.hp, removes food item from inventory and returns true, else return false
    public boolean useFood(Food food) {
        if (inv.inventoryRemove(food)) {
            food.use();
            return true;
        }
        return false;
    }

    // REQUIRES :: energyUse >= 0
    // MODIFIES :: this
    // EFFECTS :: modifies this.energy by - energyUse; returns true if the result >= 0, else return false
    public boolean forceUseEnergy(int energyUse) {
        energy = energy - energyUse;
        return energy >= 0;
    }

    // MODIFIES :: this
    // EFFECTS :: adds experience to the experience counter. If experience counter is full, level up and adjusts base stats, return true, if experience
    //            counter is not full, return false
    public boolean experienceGain(int experience) {
        this.exp = exp + experience;
        if (exp > 1000) {
            exp = exp - 1000;
            level++;
            baseStatReset();
            return true;
        }
        else return false;
    }

    // MODIFIES :: this
    // EFFECTS :: resets MC stats out of combat
    public void baseStatReset() {
        strength = STRENGTH + 5 * (level - 1);
        intel = INTEL + (level - 1);
        vitality = VITALITY + 3 * (level - 1);
        attackBase = strength/4;
        if (equipped != null) {
            attackTotal = attackBase + equipped.getDamage();
        }
        else attackTotal = attackBase;
        defenseBase = this.strength/5 + this.vitality/5;
        defenseTotal = defenseBase;
        this.maxHP = 5 * this.vitality + this.strength;
        this.maxMP = 5 * this.intel;
    }

    // MODIFIES :: this
    // EFFECTS :: calculates new attack, defense and MP levels if any stat changes occurred.
    public void combatCycleStatUpdate() {
        attackBase = strength/4;
        if (equipped != null) {
            attackTotal = attackBase + equipped.getDamage();
        }
        else attackTotal = attackBase;
        defenseBase = this.strength/5 + this.vitality/5;
        defenseTotal = defenseBase;
        mp = Math.min(mp + 20, maxMP);
    }

    public void addTechnique(Technique t) {
        techniques.add(t);
        addObserver(t);
    }

    // MODIFIES :: this
    // EFFECTS :: every time a new combat cycle is started, update combat relevant stats and notify combat techniques of a new cycle
    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        if (arg.equals(true)) {
            combatCycleStatUpdate();
            notifyObservers(true);
        }
        else {
            notifyObservers(false);
            baseStatReset();
        }
    }

    public Weapon getEquipped() { return equipped; }

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

    public int getAttackTotal() {
        return attackTotal;
    }

    public Inventory getInventory() {
        return inv;
    }

    public int getMaxHP() { return maxHP; }

    public int getDefenseTotal() { return defenseTotal; }

    public static MC getMC() {
        if (mc == null)
            mc = new MC();
        return mc;
    }

    public void setStrength(int str) {
        strength = str;
    }
}
