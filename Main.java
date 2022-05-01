//James Dirr MAT-385-001
//allows user to build a graph by inputting edges and then provides path of shortest path between two points

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        String input = "";
        String[] parts;
        int n1, n2;
        double d;
        ArrayList<Edge> listEdge = new ArrayList<>();
        System.out.println("Do you want to use a text file to enter graph? Enter 'YES' or 'NO'");
        input = scan.nextLine();
        //creates graph from file of list of edges
        if (input.equals("YES")) {
            System.out.println("Enter the name of the file. Such as 'src/Graph.txt'");
            input = scan.nextLine();
            System.out.println(input);
            File graph = new File(input);
            Scanner reader = new Scanner(graph);
            while (reader.hasNextLine()) {
                input = reader.nextLine();
                System.out.println(input);
                parts = input.split(" ");
                n1 = Integer.parseInt(parts[0]);
                n2 = Integer.parseInt(parts[1]);
                d = Double.parseDouble(parts[2]);
                System.out.println("n1 = " + n1 + " n2 = " + n2 + " d = " + d);
                listEdge.add(new Edge(n1, n2, d));
            }
        } else {
            //creates graph by manually entering edges
            while (loop) {
                System.out.println("Enter edge as 'firstNode# secondNode# distance#' or 'DONE' if you have enter all edges");
                input = scan.nextLine();
                parts = input.split(" ");
                System.out.println("part[0] is: " + parts[0]);
                if (parts[0].equals("DONE")) {
                    loop = false;
                    System.out.println("Creating graph");
                } else {
                    n1 = Integer.parseInt(parts[0]);
                    n2 = Integer.parseInt(parts[1]);
                    d = Double.parseDouble(parts[2]);
                    System.out.println("n1 = " + n1 + " n2 = " + n2 + " d = " + d);
                    listEdge.add(new Edge(n1, n2, d));
                }
            }
        }
        boolean endprog = false;
        //loops till user is done with the graph
        while (!endprog) {
            System.out.println("Enter starting node or 'DONE' to exit: ");
            input = scan.nextLine();
            if(input.equals("DONE")){
                endprog = true;
                exit(0);
            }
            int start = Integer.parseInt(input);
            System.out.println("Enter destination node: ");
            input = scan.nextLine();
            int end = Integer.parseInt(input);
            System.out.println("Searching for shortest path from node " + start + " and node " + end);
            Edge[] edges = {
            };
            edges = listEdge.toArray(edges);
            Graph g = new Graph(edges);
            g.calculateShortestDistances(start, end);
            g.calculatePath();
            g.printResult();
            String results = "";
            ArrayList<Integer> path = g.getPath();
            //gives user shortest path between the points entered
            System.out.println("Path from " + start + " to " + end + " is: ");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + ", ");
            }
            System.out.print(end + "\n");
    }
    }
}
