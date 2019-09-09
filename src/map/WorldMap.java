package map;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import protagonist.MC;

import java.util.*;

public class WorldMap {
    private static WorldMap wm = null;

    private Graph<enumLcn, String> g = new DefaultDirectedWeightedGraph<>(String.class);
    private enumLcn currL;

    private WorldMap() {
        g.addVertex(enumLcn.HOME);
        g.addVertex(enumLcn.MARKET);
        g.addVertex(enumLcn.T_GROUND);
        g.addVertex(enumLcn.T_SQUARE);
        g.addVertex(enumLcn.GARDEN);

        g.addEdge(enumLcn.HOME, enumLcn.MARKET, "h-m");
        g.setEdgeWeight("h-m", 6);
        g.addEdge(enumLcn.MARKET, enumLcn.HOME, "m-h");
        g.setEdgeWeight("m-h", 6);
        g.addEdge(enumLcn.MARKET, enumLcn.T_GROUND, "m-tg");
        g.setEdgeWeight("m-tg", 1);
        g.addEdge(enumLcn.T_GROUND, enumLcn.MARKET,"tg-m");
        g.setEdgeWeight("tg-m", 1);
        g.addEdge(enumLcn.T_GROUND, enumLcn.T_SQUARE, "tg-ts");
        g.setEdgeWeight("tg-ts", 1);
        g.addEdge(enumLcn.T_SQUARE, enumLcn.T_GROUND, "ts-tg");
        g.setEdgeWeight("ts-tg", 1);
        g.addEdge(enumLcn.T_SQUARE, enumLcn.GARDEN, "ts-g");
        g.setEdgeWeight("ts-g", 10);
        g.addEdge(enumLcn.GARDEN, enumLcn.T_SQUARE, "g-ts");
        g.setEdgeWeight("g-ts", 10);

        currL = enumLcn.HOME;
    }

    //REQUIRE: there is an edge going from currL to newL
    //MODIFIES: this, MC
    //EFFECTS: changes currL to newL and reduce MC's energy by edge weight
    public void travelTo(enumLcn newL) {
        String edge = g.getEdge(currL, newL);
        double cost = g.getEdgeWeight(edge);
        MC.getMC().energy -= cost;

        this.currL = newL;
    }

    //REQUIRE: there is an edge going from in to out
    //EFFECTS: return energy cost of going from in to out
    public double getEnergyConsumption(enumLcn in, enumLcn out) {
        return g.getEdgeWeight(g.getEdge(in, out));
    }

    //EFFECTS: return list of map.Location adjacent to currL
    public List<enumLcn> getAdjLocations() {
        Set<String> outEdges = g.outgoingEdgesOf(this.currL);
        List<enumLcn> adjLocations = new ArrayList<>();

        for (String edge : outEdges) {
            adjLocations.add(g.getEdgeTarget(edge));
        }

        return adjLocations;
    }

    public enumLcn getCurrL() {
        return currL;
    }

    // use for test only thanks
    public void setLocation(enumLcn lcn) {
        currL = lcn;
    }

    public static WorldMap getWorldMap() {
        if (wm == null) wm = new WorldMap();
        return wm;
    }

}
