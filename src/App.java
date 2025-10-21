import graph.Graph;

public class App {

    public static void main(String[] args) {
//        int vertices = 6;
//        int edges = 9;
//
//        Graph graph = new Graph(vertices, edges);
//
//        graph.addEdge(0, 1, 8);
//        graph.addEdge(0, 5, 5);
//        graph.addEdge(0, 3, 3);
//        graph.addEdge(1, 2, 6);
//        graph.addEdge(2, 4, 4);
//        graph.addEdge(3, 4, -1);
//        graph.addEdge(5, 1, -4);
//        graph.addEdge(5, 2, -1);
//        graph.addEdge(5, 4, -3);

        Graph graph = new Graph("src/graph.txt");


        graph.findShortestPaths(0);
    }
}
