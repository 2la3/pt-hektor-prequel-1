package test.locations;

import locations.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapTest {

    Map map;

    @BeforeEach
    public void mapSetup() {
        map = new Map();
    }

    @Test
    public void mapSizeTest() {
        assertTrue(map.getEdge().size() == 4);
        assertTrue(map.getVertex().size() == 5);
        System.out.println("Map size test passed");
    }


}
