import java.util.*;

public class ShortestPaths_new {
    private final Map<Integer, List<Edge>> adjList;
    private final Set<String> removedEdges; // Store removed edges

    public ShortestPaths_new() {
        this.adjList = new HashMap<>();
        this.removedEdges = new HashSet<>();
    }

    public void addEdge(int from, int to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, weight)); // Undirected Graph
    }

    public List<Integer> dijkstra(int source, int destination) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        for (int node : adjList.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        distances.put(source, 0);
        minHeap.add(new Edge(source, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int node = current.to;

            if (node == destination) break; // Stop if destination is reached

            for (Edge neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                String edgeKey = node + "-" + neighbor.to;
               // String reverseKey = neighbor.to + "-" + node;

                if (removedEdges.contains(edgeKey)) {
                    continue; // Skip removed edges
                }

                int newDist = distances.get(node) + neighbor.weight;

                if (newDist < distances.get(neighbor.to)) {
                    distances.put(neighbor.to, newDist);
                    parent.put(neighbor.to, node);
                    minHeap.add(new Edge(neighbor.to, newDist));
                }
            }
        }

        return reconstructPath(parent, source, destination);
    }

    private List<Integer> reconstructPath(Map<Integer, Integer> parent, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        if (!parent.containsKey(destination)) return path; // No path exists

        for (int at = destination; at != source; at = parent.get(at)) {
            path.add(at);
        }
        path.add(source);
        Collections.reverse(path);

        return path;
    }

    public void find3paths(int source, int destination) {
        for (int i = 1; i <= 3; i++) {
            List<Integer> path = dijkstra(source, destination);

            if (path.isEmpty()) {
                System.out.println("No " + (i == 1 ? "first" : i == 2 ? "second" : "third") + " shortest path available.");
                return;
            }

            System.out.println((i == 1 ? "First" : i == 2 ? "Second" : "Third") + " shortest path: " + path);

            // Remove edges of the found path to get a different path
            for (int j = 0; j < path.size() - 1; j++) {
                String edgeKey = path.get(j) + "-" + path.get(j + 1);
               // String reverseKey = path.get(j + 1) + "-" + path.get(j);
                removedEdges.add(edgeKey);
               // removedEdges.add(reverseKey);
            }
        }
    }

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

}
