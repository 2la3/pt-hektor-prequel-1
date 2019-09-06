package tests.map;

import map.WorldMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorldMapTest {
    private WorldMap wm;

    @BeforeEach
    public void setup() {wm = new WorldMap();}

    @Test
    public void draftTest() {
        System.out.println(wm.getAdjLocations());
    }
}
