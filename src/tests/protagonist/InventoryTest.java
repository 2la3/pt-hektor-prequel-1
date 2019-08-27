package tests.protagonist;

import items.food.Blueberries;
import items.food.Turkey;
import locations.exceptions.TravelDirectionInvalidException;
import locations.exceptions.UncheckedEnergyConsumptionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import protagonist.Inventory;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    Inventory inv;

    @BeforeEach
    public void setup() {
        inv = new Inventory();
    }

    @Test
    public void testAddingRemovingFood() {
        assertEquals(0, inv.food.size());
        Blueberries b = new Blueberries();
        Turkey t = new Turkey();
        inv.inventoryAdd(b);
        assertEquals(1, inv.food.size());
        inv.inventoryAdd(t);
        assertEquals(2, inv.food.size());
        Turkey t2 = new Turkey();
        inv.inventoryAdd(t2);
        assertEquals(2, inv.food.size());
        assertEquals(1, inv.food.get(0).value);
        assertEquals(2, inv.food.get(1).value);
        assertEquals(3, inv.getCurrentSlots());
        System.out.println("Adding Food Tests Passed");
        Turkey t3 = new Turkey();
        inv.inventoryRemove(t3);
        assertEquals(2, inv.food.size());
        assertEquals(1, inv.food.get(1).value);
        inv.inventoryRemove(b);
        assertEquals(1, inv.food.size());
        assertEquals(t.getClass(), (inv.food.get(0)).key.getClass());
        Blueberries b2 = new Blueberries();
        assertFalse(inv.inventoryRemove(b2));
        assertEquals(1, inv.food.size());
        assertEquals(1, inv.getCurrentSlots());
    }


}
