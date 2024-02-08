//226 Lab 5 June 2023

//This java file contains a template for a bipartite checker.
//Your task is to complete the code, and test your solution using the testing harness in main().
import java.util.*;

public class BipartiteChecker {
    /**
     * Checks whether or not a graph is bipartite.
     * This function should use the fact that a graph is bipartite iff it contains no odd cycles,
     * and that odd cycles can be detected by investigating cross edges in BFS.
     * No other solutions will be accepted.
     * Run time requirement: O(v+e)
     * Space requirement: O(v)
     * @param graph the graph G, represented as an adjacency list
     * @return whether G is bipartite
     */
    public static boolean isBipartite(List<List<Integer>> graph) {

        if (graph.isEmpty()) { return true; }

        int n = graph.size();
        int[] colors = new int[n];
        Arrays.fill(colors, -1); 

        for (int i = 0; i < n; i++) {
            if ((colors[i] == -1) &&
            (!isBipartiteBFS(graph, i, colors)))  // BFS Implementation
            // (!isBipartiteDFS(graph, i, colors, 0))) // DFS Implementation
            { return false; }
        }
        return true;
    }

    // BFS Implementation
    private static boolean isBipartiteBFS(List<List<Integer>> adjacencyList, int startVertex, int[] vertexColors) {
    Stack<Integer> stack = new Stack<>();
    stack.push(startVertex);
    vertexColors[startVertex] = 0;

    while (!stack.isEmpty()) {
        int currentVertex = stack.pop();
        for (int neighbor : adjacencyList.get(currentVertex)) {
            if (vertexColors[neighbor] == -1) {
                vertexColors[neighbor] = 1 - vertexColors[currentVertex];
                stack.push(neighbor);
            } else if (vertexColors[neighbor] == vertexColors[currentVertex]) 
            { return false; }
        }
    }
    return true;
}

/* 
    // DFS Implementation
    private static boolean isBipartiteDFS(List<List<Integer>> graph, int vertex, int[] visited, int color) {
        // Assign the color to the current vertex
        visited[vertex] = color; 

        for (int neighbor : graph.get(vertex)) {
            if (visited[neighbor] == color) { return false; } 
            
            if ((visited[neighbor] == -1) &&
            (!isBipartiteDFS(graph, neighbor, visited, 1 - color))) 
            { return false; } 
        }

        return true;
    }
*/

    public static void main(String[] args) {
        // Empty graph - T
        List<List<Integer>> graph = new ArrayList<>();
        
        boolean isBipartite = isBipartite(graph);
        if (isBipartite) {
            System.out.println("The empty graph is bipartite.");
        } else {
            System.out.println("The empty graph is not bipartite.");
        }

        // Single vertex - T
        graph = new ArrayList<>();
        graph.add(Arrays.asList());

        isBipartite = isBipartite(graph);
        if (isBipartite) {
            System.out.println("The single vertex graph is bipartite.");
        } else {
            System.out.println("The single vertex is not bipartite.");
        }
        
        // Line graph - T
        graph = new ArrayList<>();
        graph.add(Arrays.asList(1));
        graph.add(Arrays.asList(0,2));
        graph.add(Arrays.asList(1));

        isBipartite = isBipartite(graph);
        if (isBipartite) {
            System.out.println("The line graph is bipartite.");
        } else {
            System.out.println("The line graph is not bipartite.");
        }

        // Square graph - T
        graph = new ArrayList<>();
        graph.add(Arrays.asList(1, 3));
        graph.add(Arrays.asList(0, 2));
        graph.add(Arrays.asList(1, 3));
        graph.add(Arrays.asList(0, 2));

        isBipartite = isBipartite(graph);
        if (isBipartite) {
            System.out.println("The square graph is bipartite.");
        } else {
            System.out.println("The square graph is not bipartite.");
        }

        // Connected bipartite graph - T
        graph = new ArrayList<>();
        graph.add(Arrays.asList(3, 4, 5, 6));
        graph.add(Arrays.asList(3, 4, 5, 6));
        graph.add(Arrays.asList(3, 4, 5, 6));
        graph.add(Arrays.asList(0, 1, 2));
        graph.add(Arrays.asList(0, 1, 2));
        graph.add(Arrays.asList(0, 1, 2));
        graph.add(Arrays.asList(0, 1, 2));

        isBipartite = isBipartite(graph);
        if (isBipartite) {
            System.out.println("The fully connected graph is bipartite.");
        } else {
            System.out.println("The fully connected graph is not bipartite.");
        }

        // Pentagon graph - F
        graph = new ArrayList<>();
        graph.add(Arrays.asList(1,4));
        graph.add(Arrays.asList(0,2));
        graph.add(Arrays.asList(1,3));
        graph.add(Arrays.asList(2,4));
        graph.add(Arrays.asList(0,3));

        isBipartite = isBipartite(graph);
        if (isBipartite) {
            System.out.println("The pentagon graph is bipartite.");
        } else {
            System.out.println("The pentagon graph is not bipartite.");
        }
        
        // Petersen graph - F
        graph = new ArrayList<>();
        graph.add(Arrays.asList(1, 2, 3));
        graph.add(Arrays.asList(0, 4, 5));
        graph.add(Arrays.asList(0, 4, 6));
        graph.add(Arrays.asList(0, 6, 7));
        graph.add(Arrays.asList(1, 2, 8));
        graph.add(Arrays.asList(1, 7, 8));
        graph.add(Arrays.asList(2, 3, 9));
        graph.add(Arrays.asList(3, 5, 9));
        graph.add(Arrays.asList(4, 5, 9));
        graph.add(Arrays.asList(6, 7, 8));

        isBipartite = isBipartite(graph);
        if (isBipartite) {
            System.out.println("The Petersen graph is bipartite.");
        } else {
            System.out.println("The Petersen graph is not bipartite.");
        }

    }
}