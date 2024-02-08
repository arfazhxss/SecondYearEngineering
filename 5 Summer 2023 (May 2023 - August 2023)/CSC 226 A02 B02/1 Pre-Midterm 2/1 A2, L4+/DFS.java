
// Java program to check if removing an
// edge disconnects a graph or not.
import java.util.*;

// Graph class represents a directed graph
// using adjacency list representation
class Graph {
	int V; // No. of vertices
	ArrayList<ArrayList<Integer>> adj;

	private void DFS(int v, boolean[] visited) {
		// Mark the current node as visited and print it
		visited[v] = true;
		// Recur for all the vertices adjacent to
		// this vertex
		for (Integer i : adj.get(v)) {
			if (!visited[i]) {
				DFS(i, visited);
			}
		}
	}

	public Graph(int V) {
		this.V = V;
		adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
	}

	public void addEdge(int u, int v) {
		adj.get(u).add(v); // Add v to u’s list.
		adj.get(v).add(u); // Add u to v’s list.
	}

	// Returns true if given graph is connected, else false
	public boolean isConnected() {
		boolean[] visited = new boolean[V];

		// Find all reachable vertices from first vertex
		DFS(0, visited);

		// If set of reachable vertices includes all,
		// return true.
		for (int i = 1; i < V; i++)
			if (visited[i] == false)
				return false;

		return true;
	}

	// This function assumes that edge (u, v)
	// exists in graph or not,
	public boolean isBridge(int u, int v) {
		// Remove edge from undirected graph
		adj.get(u).remove(Integer.valueOf(v));
		adj.get(v).remove(Integer.valueOf(u));

		boolean res = isConnected();

		// Adding the edge back
		addEdge(u, v);

		// Return true if graph becomes disconnected
		// after removing the edge.
		return (res == false);
	}

	public int countBridges() {
		int bridgeCount = 0;

		for (int u = 0; u < V; u++) {
			List<Integer> neighbors = new ArrayList<>(adj.get(u));
			for (int v : neighbors) {
				// Remove edge from undirected graph
				adj.get(u).remove(Integer.valueOf(v));
				adj.get(v).remove(Integer.valueOf(u));

				// Check if graph becomes disconnected
				boolean isConnected = isConnected();

				// Adding the edge back
				addEdge(u, v);

				// Increment the bridge count if the graph becomes disconnected
				if (!isConnected) {
					bridgeCount++;
				}
			}
		}

		return bridgeCount;
	}

	public int countNonBridges() {
		int nonBridges = 0;

		for (int u = 0; u < V; u++) {
			List<Integer> neighbors = new ArrayList<>(adj.get(u));
			for (int v : neighbors) {
				// Remove edge from undirected graph
				adj.get(u).remove(Integer.valueOf(v));
				adj.get(v).remove(Integer.valueOf(u));

				// Check if graph becomes disconnected
				boolean isConnected = isConnected();

				// Adding the edge back
				addEdge(u, v);

				// Increment the bridge count if the graph becomes disconnected
				if (isConnected) {
					nonBridges++;
				}
			}
		}

		return nonBridges;
	}

	// Driver code
	public static void main(String[] args) {
		Graph g = new Graph(10);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(5, 6);
		g.addEdge(6, 7);
		g.addEdge(7, 8);
		g.addEdge(8, 9);

		// Add an additional edge to create a bridge
		g.addEdge(2, 4);

		// Add an additional edge to create a non-bridge
		g.addEdge(1, 3);

		int numBridges = g.countBridges();
		System.out.println("Number of bridges in the graph: " + numBridges);

		int nonBridges = g.countNonBridges();
		System.out.println("Number of non-bridges in the graph: " + nonBridges);

	}
}
