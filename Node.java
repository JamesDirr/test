//James Dirr MAT-385-001
//Node Object

import java.util.ArrayList;

public class Node {
    private double distance = Double.MAX_VALUE;
    private boolean visited;
    private int predecessor;
    private ArrayList<Edge> edges = new ArrayList<>();
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public boolean isVisited() {
        return !visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public ArrayList<Edge> getEdges() {
        return edges;
    }
    public int getPredecessor(){
        return predecessor;
    }
    public void setPredecessor(int predecessor){
        this.predecessor = predecessor;
    }
}