package locations;

import locations.exceptions.TravelDirectionInvalidException;
import locations.exceptions.UncheckedEnergyConsumptionException;

import java.util.ArrayList;

public class Map {

    // locationID:
    // Home = 0, Market = 1, Master = 2, Mayor = 3, Garden = 4
    private ArrayList<Vertex> v;
    private ArrayList<Edge> e;
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

    public int getCurrentLocationID() {
        return currentLocationID;
    }

    public ArrayList<Vertex> getVertex() {
        return v;
    }

    public ArrayList<Edge> getEdge() {
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

    private class Vertex {

        final int locationID;

        private Vertex(int id) {
            locationID = id;
        }
    }
}
