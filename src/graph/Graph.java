package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    int vertices;
    List<Edge> edgeList;

    public Graph(int v) {
        this.vertices = v;
        this.edgeList = new ArrayList<>();
    }

    public Graph(String filePath) {
        this.edgeList = new ArrayList<>();
        loadFromFile(filePath);
    }

    private void loadFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){

            List<int[]> matrix = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                int[] row = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    if(tokens[i].equals("INF")) {
                        row[i] = Integer.MAX_VALUE;
                    } else {
                        row[i] = Integer.parseInt(tokens[i]);
                    }
                }
                matrix.add(row);
            }

            convertMatrixToEdgeList(matrix);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private void convertMatrixToEdgeList(List<int[]> matrix) {
        this.vertices = matrix.size();
        // Converter matriz de adjacência em lista de arestas
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matrix.get(i)[j] != Integer.MAX_VALUE && i != j) {
                    addEdge(i, j, matrix.get(i)[j]);
                }
            }
        }
    }

    public void addEdge(int source, int destiny, int weight) {
        this.edgeList.add(new Edge(source, destiny, weight));
    }

    public void findShortestPaths(int source) {
        // Passo 1: Inicializar as distâncias
        // Cria um array de distâncias com tamanho V
        int[] dist = new int[this.vertices];

        // Inicializa todas as distâncias como "infinito"
        Arrays.fill(dist, Integer.MAX_VALUE);

        // A distância da origem para ela mesma é 0
        dist[source] = 0;

        // Passo 2: Relaxar todas as arestas V-1 vezes
        for (int i = 0; i < this.vertices - 1; i++) {
            for (Edge edge : this.edgeList) {
                int u = edge.source;
                int v = edge.destiny;
                int w = edge.weight;

                // Se a distância para u não é infinito e a distância para v pode ser reduzida
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Passo 3: Verificar ciclos negativos
        for (Edge edge : this.edgeList) {
            int u = edge.source;
            int v = edge.destiny;
            int w = edge.weight;

            // Se a distância para u não é infinito e a distância para v pode ser reduzida
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        printDistances(dist, source);
    }

    private void printDistances(int[] dist, int src) {
        System.out.println("Distância mais curta do vértice " + src + " para todos os outros:");
        System.out.println("Vértice \t Distância");
        for (int i = 0; i < this.vertices; ++i) {
            String distance = (dist[i] == Integer.MAX_VALUE) ? "INF" : String.valueOf(dist[i]);
            System.out.println(i + "\t\t" + distance);
        }
    }


}
