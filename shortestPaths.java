import java.util.*;

public class shortestPaths {
    private final Map<Integer, List<Edge>> adjList;
    private final Set<Integer> visited; // Stores nodes from the first path

    public shortestPaths() {
        this.adjList = new HashMap<>();
        this.visited = new HashSet<>();
    }

    public void addEdge(int from, int to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, weight)); // Undirected Graph
    }

    public List<Integer> dijkstra(int source, int destination, boolean excludeVisited) {
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
            if (excludeVisited && visited.contains(node)) continue; // Skip visited nodes

            for (Edge neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                if (excludeVisited && visited.contains(neighbor.to)) continue; // Skip visited nodes

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

    public void findthreeShortestPaths(int source, int destination) {
        // First shortest path
        List<Integer> firstPath = dijkstra(source, destination, false);

        if (firstPath.isEmpty()) {
            System.out.println("No path found.");
            return;
        }

        System.out.println("First shortest path: " + firstPath);

        // Marking nodes in the first path as visited
        visited.addAll(firstPath);
        visited.remove(destination); // Remove destination node
        visited.remove(source); // Remove source node

        // Second shortest path (excluding visited nodes)
        List<Integer> secondPath = dijkstra(source, destination, true);
        
        if (secondPath.isEmpty()) {
            System.out.println("No second shortest path available.");
        } else {
            System.out.println("Second shortest path: " + secondPath);
        }
        
        visited.addAll(secondPath);
        visited.remove(destination); // Remove destination node
        visited.remove(source); // Remove source node

        List<Integer> thirdPath = dijkstra(source, destination, true);

        if (thirdPath.isEmpty()) {
            System.out.println("No third  shortest path available.");
        } else {
            System.out.println("Third shortest path: " + thirdPath);
        }
    }

    static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // public static void main(String[] args) {
    //    // shortestPaths g = new shortestPaths();
       

        
    // }
}
