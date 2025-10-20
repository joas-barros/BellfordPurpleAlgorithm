import graph.Graph;

public class App {

    public static void main(String[] args) {
        int vertices = 5;
        int edges = 8;

        Graph graph = new Graph(vertices, edges);

        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        graph.findShortestPaths(0);
    }
}
