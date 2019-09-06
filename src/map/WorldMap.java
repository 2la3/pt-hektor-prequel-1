package map;

import locations.Location;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;
import protagonist.MC;

import java.io.*;
import java.net.*;
import java.util.*;

public class WorldMap {

    private Graph<Location, String> g = new DefaultDirectedWeightedGraph<>(String.class);
    private Location currL;

    public WorldMap() {
        g.addVertex(Location.HOME);
        g.addVertex(Location.MARKET);
        g.addVertex(Location.T_GROUND);
        g.addVertex(Location.T_SQUARE);
        g.addVertex(Location.GARDEN);

        g.addEdge(Location.HOME, Location.MARKET, "h-m");
        g.setEdgeWeight("h-m", 6);
        g.addEdge(Location.MARKET, Location.HOME, "m-h");
        g.setEdgeWeight("m-h", 6);
        g.addEdge(Location.MARKET, Location.T_GROUND, "m-tg");
        g.setEdgeWeight("m-tg", 1);
        g.addEdge(Location.T_GROUND, Location.MARKET,"tg-m");
        g.setEdgeWeight("tg-m", 1);
        g.addEdge(Location.T_GROUND, Location.T_SQUARE, "tg-ts");
        g.setEdgeWeight("tg-ts", 1);
        g.addEdge(Location.T_SQUARE, Location.T_GROUND, "ts-tg");
        g.setEdgeWeight("ts-tg", 1);
        g.addEdge(Location.T_SQUARE, Location.GARDEN, "ts-g");
        g.setEdgeWeight("ts-g", 10);
        g.addEdge(Location.GARDEN, Location.T_SQUARE, "g-ts");
        g.setEdgeWeight("g-ts", 10);

        currL = Location.HOME;
    }

    //REQUIRE: there is an edge going from currL to newL
    //MODIFIES: this, MC
    //EFFECTS: changes currL to newL and reduce MC's energy by edge weight
    public void travelTo(Location newL) {
        String edge = g.getEdge(currL, newL);
        double cost = g.getEdgeWeight(edge);
        MC.getMC().energy -= cost;

        this.currL = newL;
    }

    //REQUIRE: there is an edge going from in to out
    //EFFECTS: return energy cost of going from in to out
    public double getEnergyConsumption(Location in, Location out) {
        return g.getEdgeWeight(g.getEdge(in, out));
    }

    //EFFECTS: return list of Location adjacent to currL
    public List<Location> getAdjLocations() {
        Set<String> outEdges = g.outgoingEdgesOf(this.currL);
        List<Location> adjLocations = new ArrayList<>();

        for (String edge : outEdges) {
            adjLocations.add(g.getEdgeTarget(edge));
        }

        return adjLocations;
    }

    public Location getCurrL() {
        return currL;
    }
}
