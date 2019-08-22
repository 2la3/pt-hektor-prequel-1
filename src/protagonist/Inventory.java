package protagonist;

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
    private int maxSlots = 24;
    public int currentSlots = 0;

    // MODIFIES :: this
    // EFFECTS :: attempts to add the specified item to the inventory. If successful, return true; else return false.
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
        throw new InventoryAdditionUnexpectedResultException();
    }

    // MODIFIES :: this
    // EFFECTS :: returns true if item is removed from the inventory, false otherwise
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
        return false;
    }

    public class ModPair<K, V> implements Serializable {

        public K key;
        public V value;

        public ModPair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


}
