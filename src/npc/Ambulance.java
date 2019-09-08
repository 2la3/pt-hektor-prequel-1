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
        map.returnHome();
        Home.ambulanceRest();
        return cost;
    }

    // EFFECTS :: heals MC to 1 hp, subtracts service cost from MC.inv; returns the service cost
    public static int serveHP() {
        int cost = 200 * MC.getMC().level;
        MC.getMC().getInventory().forceCoinsModifier(-cost);
        MC.getMC().hp = 1;
        return cost;
    }

    public static int serveHPCost() { return (200 * MC.getMC().level); }
}
