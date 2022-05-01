//James Dirr MAT-385-001
//edge object

public class Edge {
    private int fromIndex;
    private int toIndex;
    private double length;

    //creates edge
    public Edge(int fromIndex, int toIndex, double length) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.length = length;
    }
    public int getFromIndex() {
        return fromIndex;
    }
    public int getToIndex() {
        return toIndex;
    }
    public double getLength() {
        return length;
    }

    //finds neighbor edges
    public int getNeighbourIndex(int nodeIndex) {
        if (this.fromIndex == nodeIndex) {
            return this.toIndex;
        } else {
            return this.fromIndex;
        }
    }
}