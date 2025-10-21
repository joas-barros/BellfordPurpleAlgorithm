import graph.Graph;

public class App {

    public static void main(String[] args) {
        Graph graph = new Graph("src/graph.txt");
        graph.findShortestPaths(0);
    }
}
