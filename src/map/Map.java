package map;

import locations.exceptions.CurrentLocationInvalidException;
import locations.exceptions.TravelDirectionInvalidException;
import locations.exceptions.UncheckedEnergyConsumptionException;
import protagonist.MC;

import java.util.ArrayList;
import java.util.List;

public class Map {

    // locationID:
    // Home = 0, Market = 1, Training Ground = 2, Town Square = 3, Garden = 4
    private List<Vertex> v;
    private List<Edge> e;
    private int currentLocationID;

    // MODIFIES: this
    // EFFECTS: initializes the map
    public Map() {
        v = new ArrayList<>();
        e = new ArrayList<>();
        Vertex temp;
        temp = new Vertex(0);
        v.add(temp);
        temp = new Vertex(1);
        v.add(temp);
        temp = new Vertex(2);
        v.add(temp);
        temp = new Vertex(3);
        v.add(temp);
        temp = new Vertex(4);
        v.add(temp);
        Edge et;
        et = new Edge(v.get(0), v.get(1), 6);
        e.add(et);
        et = new Edge(v.get(1), v.get(2), 1);
        e.add(et);
        et = new Edge(v.get(2), v.get(3), 1);
        e.add(et);
        et = new Edge(v.get(3), v.get(4), 10);
        e.add(et);
        currentLocationID = 0;
    }

    // REQUIRES: direction = "LEFT" or direction = "RIGHT"
    // MODIFIES: this
    // EFFECTS: changes the current location on the map
    public void travel(String direction) throws TravelDirectionInvalidException {
        if (direction.equals("LEFT") && (currentLocationID != 0)) {
            currentLocationID--;
        }
        if (direction.equals("RIGHT") && (currentLocationID != 4)) {
            currentLocationID++;
        }
        else throw new TravelDirectionInvalidException();
    }

    // REQUIRES: 4 >= from and to >= 0, from and to have a difference of exactly 1
    // EFFECTS: returns the energy consumption needed to travel between the two locations
    public int getEnergyConsumption(int from, int to) {
        for (Edge edge : e) {
            if (edge.v1.locationID == from) {
                if (edge.v2.locationID == to) {
                    return edge.weight;
                }
            }
            else if (edge.v1.locationID == to) {
                if (edge.v2.locationID == from) {
                    return edge.weight;
                }
            }
        }
        throw new UncheckedEnergyConsumptionException();
    }

    // EFFECTS :: returns a list representing all of the locations that can be reached from the current location
    public List<String> getAdjLocations() {
        ArrayList<Integer> adj = new ArrayList<>();
        ArrayList<String> locs = new ArrayList<>();
        if (0 <= currentLocationID - 1) adj.add(currentLocationID - 1);
        if (currentLocationID + 1 <= 4) adj.add(currentLocationID + 1);
        for (Integer i : adj) {
            locs.add(getNameByID(i));
        }
        return locs;
    }

    // REQUIRES :: travelling to locName is possible from the current location;
    // MODIFIES :: this, MC
    // EFFECTS :: changes current location to locName; adjusts energy in MC, if not enough energy to travel to locName, return home
    public void travelTo(String locName) {
        int prevLoc = currentLocationID;
        if (locName.equals("Home")) currentLocationID = 0;
        if (locName.equals("Market")) currentLocationID = 1;
        if (locName.equals("Training Ground")) currentLocationID = 2;
        if (locName.equals("Town Square")) currentLocationID = 3;
        if (locName.equals("Garden")) currentLocationID = 4;
        int energy = getEnergyConsumption(prevLoc, currentLocationID);
        MC.getMC().energy = MC.getMC().energy - energy;
        if (MC.getMC().energy < 0) {
            currentLocationID = 0;
        }
    }

    // REQUIRES :: locName is one of "Home", "Market", "Training Ground", "Town Square" or "Garden"
    // MODIFIES :: this, MC
    // EFFECTS :: changes current location to locName; adjusts energy in MC and return true, if not enough energy to travel to locName return false
    public boolean travelToAuto(String locName) {
        int prevLoc = currentLocationID;
        int travelTo = getIDbyName(locName);
        if (prevLoc == travelTo) return true;
        else if (prevLoc > travelTo) {
            int energy = getEnergyConsumption(prevLoc, prevLoc - 1);
            if (MC.getMC().forceUseEnergy(energy)) {
                currentLocationID--;
                return travelToAuto(locName);
            }
        }
        else if (prevLoc < travelTo) {
            int energy = getEnergyConsumption(prevLoc, prevLoc + 1);
            if (MC.getMC().forceUseEnergy(energy)) {
                currentLocationID++;
                return travelToAuto(locName);
            }
        }
        return false;
    }

    public int getCurrentLocationID() {
        return currentLocationID;
    }

    public void returnHome() {
        currentLocationID = 0;
    }

    // REQUIRES :: 0 <= id <= 4
    // MODIFIES :: none
    // EFFECTS :: returns the String representing the location id
    public String getNameByID(int id) {
        if (id == 0) return "Home";
        else if (id == 1) return "Market";
        else if (id == 2) return "Training Ground";
        else if (id == 3) return "Town Square";
        else if (id == 4) return "Garden";
        else throw new CurrentLocationInvalidException();
    }

    // REQUIRES :: name is one of "Home", "Market", "Training Ground", "Town Square" or "Garden"
    // MODIFIES :: none
    // EFFECTS :: returns the locationID of the location represented by name
    public int getIDbyName(String name) {
        if (name.equals("Home")) return 0;
        else if (name.equals("Market")) return 1;
        else if (name.equals("Training Ground")) return 2;
        else if (name.equals("Town Square")) return 3;
        else if (name.equals("Garden")) return 4;
        else throw new CurrentLocationInvalidException();
    }

    public List<Vertex> getVertex() {
        return v;
    }

    public List<Edge> getEdge() {
        return e;
    }

    private class Edge {

        final Vertex v1;
        final Vertex v2;
        int weight;

        private Edge(Vertex v1, Vertex v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

    public class Vertex {

        public final int locationID;

        private Vertex(int id) {
            locationID = id;
        }
    }
}
