package protagonist;

import com.sun.org.apache.xpath.internal.operations.Mod;
import items.Item;
import items.food.Blueberries;
import items.food.Food;
import items.weapons.Weapon;
import protagonist.exceptions.InventoryAdditionUnexpectedResultException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public List<ModPair<Food,Integer>> food = new ArrayList<>();
    public List<ModPair<Weapon,Integer>> weapon = new ArrayList<>();
    public List<ModPair<Item, Integer>> other = new ArrayList<>();
    private int coins = 2000;

    private int maxSlots = 24;
    private int currentSlots = 0;

    // MODIFIES :: this
    // EFFECTS  :: adjusts coin balance by amount and returns true UNLESS adjusting by amount makes the balance negative, in which case don't
    //             adjust and return false.
    public boolean coinsModifier(int amount) {
        if (coins + amount < 0) return false;
        coins = coins + amount;
        return true;
    }

    // MODIFIES :: this
    // EFFECTS :: modifies coin balance by amount
    public void forceCoinsModifier(int amount) {
        coins = coins + amount;
    }

    public boolean enoughCoins(int amount) {
        return (coins + amount > 0);
    }

    // MODIFIES :: this
    // EFFECTS :: attempts to add the specified item to the inventory. If successful, return true; else return false (i.e. inventory is full)
    public boolean inventoryAdd(Item item) {
        if (currentSlots >= maxSlots) return false;
        if (item.getClass().getSuperclass().toString().equals("class items.food.Food")) {
            for (ModPair<Food, Integer> flist : food) {
                if (flist.key.equals(item)) {
                    flist.value++;
                    currentSlots++;
                    return true;
                }
            }
            Food fd = (Food) item;
            ModPair<Food, Integer> mp = new ModPair<Food, Integer>(fd, 1);
            food.add(mp);
            currentSlots++;
            return true;
        }
        if (item.getClass().getSuperclass().toString().equals("class items.weapons.Weapon")) {
            for (ModPair<Weapon, Integer> flist : weapon) {
                if (flist.key.equals(item)) {
                    flist.value++;
                    currentSlots++;
                    return true;
                }
            }
            Weapon wp = (Weapon) item;
            ModPair<Weapon, Integer> mp = new ModPair<Weapon, Integer>(wp, 1);
            weapon.add(mp);
            currentSlots++;
            return true;
        }
        else {
            for (ModPair<Item, Integer> flist : other) {
                if (flist.key.equals(item)) {
                    flist.value++;
                    currentSlots++;
                    return true;
                }
            }
            ModPair<Item, Integer> mp = new ModPair<>(item, 1);
            other.add(mp);
            currentSlots++;
            return true;
        }
    }

    // MODIFIES :: this
    // EFFECTS :: returns true if item is removed from the inventory, false otherwise (i.e. item does not exist in the inventory)
    public boolean inventoryRemove(Item item) {
        if (currentSlots <= 0) return false;
        if (item.getClass().getSuperclass().toString().equals("class items.food.Food")) {
            for (ModPair<Food, Integer> flist : food) {
                if (flist.key.equals(item)) {
                    if (flist.value > 1) {
                        flist.value--;
                    }
                    else {
                        food.remove(flist);
                    }
                    currentSlots--;
                    return true;
                }
            }
        }
        if (item.getClass().getSuperclass().toString().equals("class items.weapons.Weapon")) {
            for (ModPair<Weapon, Integer> flist : weapon) {
                if (flist.key.equals(item)) {
                    if (flist.value > 1) {
                        flist.value--;
                    } else {
                        weapon.remove(flist);
                    }
                    currentSlots--;
                    return true;
                }
            }
        }
        else {
            for (ModPair<Item, Integer> flist : other) {
                if (flist.key.equals(item)) {
                    if (flist.value > 1) {
                        flist.value--;
                    }
                    else {
                        other.remove(flist);
                    }
                    currentSlots--;
                    return true;
                }
            }
        }
        return false;
    }

    public int getCurrentSlots() { return currentSlots; }

    public int getMaxSlots() { return maxSlots; }

    public int getCoins() { return coins; }

    public class ModPair<K, V> implements Serializable {

        public K key;
        public V value;

        public ModPair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


}
