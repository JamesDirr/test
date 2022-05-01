//James Dirr MAT-385-001
//graph object

import java.util.ArrayList;

public class Graph {
    private Node[] nodes;
    private int numNodes;
    private Edge[] edges;
    private int numEdges;

    private int start;
    private int end;

    public ArrayList<Integer> path = new ArrayList<>();

    public Graph(Edge[] edges) {
        //adds nodes
        this.edges = edges;
        this.numNodes = calculateNoOfNodes(edges);
        this.nodes = new Node[this.numNodes];
        for (int n = 0; n < this.numNodes; n++) {
            this.nodes[n] = new Node();
        }
        // add all the edges to graph
        this.numEdges = edges.length;
        for (int edgeToAdd = 0; edgeToAdd < this.numEdges; edgeToAdd++) {
            this.nodes[edges[edgeToAdd].getFromIndex()].getEdges().add(edges[edgeToAdd]);
            this.nodes[edges[edgeToAdd].getToIndex()].getEdges().add(edges[edgeToAdd]);
        }
    }
    private int calculateNoOfNodes(Edge[] edges) {
        int numNodes = 0;
        for (Edge e : edges) {
            if (e.getToIndex() > numNodes)
                numNodes = e.getToIndex();
            if (e.getFromIndex() > numNodes)
                numNodes = e.getFromIndex();
        }
        numNodes++;
        return numNodes;
    }

    public void calculateShortestDistances(int startAt, int endAt) {
        this.start = startAt;
        this.end = endAt;
        this.nodes[startAt].setDistance(0);
        int nextNode = startAt;
        // visit every node
        for (int i = 0; i < this.nodes.length; i++) {
            // loop around the edges of current node
            ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
            for (Edge currentNodeEdge : currentNodeEdges) {
                int neighbourIndex = currentNodeEdge.getNeighbourIndex(nextNode);
                //  if not visited
                if (this.nodes[neighbourIndex].isVisited()) {
                    double tentative = this.nodes[nextNode].getDistance() + currentNodeEdge.getLength();
                    if (tentative < nodes[neighbourIndex].getDistance()) {
                        nodes[neighbourIndex].setDistance(tentative);
                        nodes[neighbourIndex].setPredecessor(nextNode);
                    }
                }

            }
            nodes[nextNode].setVisited(true);
            nextNode = getNodeShortestDistanced();
        }
    }

    //returns shortest distance of a node
    private int getNodeShortestDistanced() {
        int storedNodeIndex = 0;
        double storedDist = Integer.MAX_VALUE;
        for (int i = 0; i < this.nodes.length; i++) {
            double currentDist = this.nodes[i].getDistance();
            if (this.nodes[i].isVisited() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }

    //creates path of shortest distance
    public void calculatePath(){
        int nodeNow = end;
        while(nodeNow != start){
            path.add(nodes[nodeNow].getPredecessor());
            nodeNow = nodes[nodeNow].getPredecessor();
        }
    }
    //returns the path
    public ArrayList<Integer> getPath(){
        return path;

    }
    // display result
    public void printResult() {
        String output = "Number of nodes = " + this.numNodes;
        output += "\nNumber of edges = " + this.numEdges;
        output += "\nDistance from "+start+" to "+end+" is " + (String.format("%,.2f",nodes[end].getDistance()));
        System.out.println(output);
    }

}