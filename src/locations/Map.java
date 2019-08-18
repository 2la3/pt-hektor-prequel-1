package locations;

import java.util.ArrayList;

public class Map {

    // locationID:
    // Home = 0, Market = 1, Master = 2, Mayor = 3, Garden = 4
    private ArrayList<Vertex> v;
    private ArrayList<Edge> e;

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
    }

    public ArrayList<Vertex> getVertex() {
        return v;
    }

    public ArrayList<Edge> getEdge() {
        return e;
    }

    private class Edge {

        Vertex v1;
        Vertex v2;
        int weight;

        private Edge(Vertex v1, Vertex v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

    private class Vertex {

        int locationID;

        private Vertex(int id) {
            locationID = id;
        }
    }
}
