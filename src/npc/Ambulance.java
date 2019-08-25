package npc;

import locations.Home;
import map.Map;
import protagonist.MC;

public class Ambulance extends NPC {

    // REQUIRES :: map
    // MODIFIES :: Map, MC.inv
    // EFFECTS :: moves MC to Home, subtracts service cost from MC.inv; returns the service cost
    public static int serveEnergy (Map map) {
        int cost = map.getCurrentLocationID() * MC.getMC().level * 50;
        MC.getMC().getInventory().forceCoinsModifier(-cost);
        Home.ambulanceRest();
        return cost;
    }
}
