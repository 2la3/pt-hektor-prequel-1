package tests.map;
import ui.locations.exceptions.TravelDirectionInvalidException;
import ui.locations.exceptions.UncheckedEnergyConsumptionException;
import map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    Map map;

    @BeforeEach
    public void mapSetup() {
        map = new Map();
    }

    @Test
    public void mapSizeTest() {
        assertEquals(4, map.getEdge().size());
        assertEquals(5, map.getVertex().size());
        System.out.println("map.Map size test passed");
    }

    @Test
    public void travelDirectionTest0() {
        try {
            map.travel("UP");
        }
        catch (TravelDirectionInvalidException exc) {
            System.out.println("Travel Direction Invalid Input at 0 Passed");
        }
        try {
            map.travel("LEFT");
        }
        catch (TravelDirectionInvalidException exc) {
            assertEquals(0, map.getCurrentLocationID());
            System.out.println("Travel Direction Invalid LEFT at 0 Passed");
        }
        try {
            map.travel("RIGHT");
            assertEquals(1, map.getCurrentLocationID());
        }
        catch (TravelDirectionInvalidException exc) {
            fail ("Unexpected Exception travelling RIGHT at 0");
        }
    }

    @Test
    public void travelDirectionTestMiddle2() {
        try {
            map.travel("UP");
        }
        catch (TravelDirectionInvalidException exc) {
            System.out.println("Travel Direction Invalid Input at 2 Passed");
        }
        try {
            map.travel("RIGHT");
            map.travel("RIGHT");
        }
        catch (TravelDirectionInvalidException exc) {
            System.out.println("Unexpected Exception Setting up travelDirectionTestMiddle2");
        }
        try {
            map.travel("LEFT");
            assertEquals(1, map.getCurrentLocationID());
        }
        catch (TravelDirectionInvalidException exc) {
            System.out.println("Unexpected Exception travelling LEFT at 2");
        }
        try {
            map.travel("RIGHT");
            map.travel("RIGHT");
            assertEquals(3, map.getCurrentLocationID());
        }
        catch (TravelDirectionInvalidException exc) {
            fail ("Unexpected Exception travelling RIGHT at 2");
        }
    }

    @Test
    public void travelDirectionTest4() {
        try {
            map.travel("UP");
        }
        catch (TravelDirectionInvalidException exc) {
            System.out.println("Travel Direction Invalid Input at 4 Passed");
        }
        try {
            map.travel("LEFT");
            assertEquals(3, map.getCurrentLocationID());
        }
        catch (TravelDirectionInvalidException exc) {
            System.out.println("Unexpected Exception travelling LEFT at 4");
        }
        try {
            map.travel("RIGHT");
        }
        catch (TravelDirectionInvalidException exc) {
            assertEquals(4, map.getCurrentLocationID());
            System.out.println("Travel direction Invalid RIGHT at 4 passed");
        }
    }

    @Test
    public void getEnergyConsumptionTest() {
        try {
            map.getEnergyConsumption(2,2);
        }
        catch (UncheckedEnergyConsumptionException exc) {
            System.out.println("Expected Exception Caught, Invalid input, from == to");
        }
        try {
            map.getEnergyConsumption(4,5);
        }
        catch (UncheckedEnergyConsumptionException exc) {
            System.out.println("Expected Exception Caught, Invalid input: out of bounds");
        }
        try {
            map.getEnergyConsumption(-1, 0);
        }
        catch (UncheckedEnergyConsumptionException exc) {
            System.out.println("Expected Exception Caught, Invalid input: out of bounds");
        }
        try {
            map.getEnergyConsumption(3,0);
        }
        catch (UncheckedEnergyConsumptionException exc) {
            System.out.println("Expected Exception Caught, Invalid input: from and to do not have a difference of exactly 1");
        }
        try {
            assertEquals(10, map.getEnergyConsumption(3,4));
            assertEquals(10, map.getEnergyConsumption(4,3));
            assertEquals(6, map.getEnergyConsumption(0,1));
            assertEquals(1, map.getEnergyConsumption(3,2));
            System.out.println("getEnergyConsumptionTest Passed");
        }
        catch (UncheckedEnergyConsumptionException exc) {
            fail("Unexpected Exception");
        }
    }


}
