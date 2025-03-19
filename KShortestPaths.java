import java.util.*;

class Edge {
    int target;
    double weight;

    public Edge(int target, double weight) {
        this.target = target;
        this.weight = weight;
    }
}

class Path implements Comparable<Path> {
    List<Integer> nodes;
    double cost;

    public Path(List<Integer> nodes, double cost) {
        this.nodes = new ArrayList<>(nodes);
        this.cost = cost;
    }

    @Override
    public int compareTo(Path other) {
        return Double.compare(this.cost, other.cost);
    }
}

class KShortestPaths {
    private Map<Integer, List<Edge>> graph;
    private int k;

    public KShortestPaths(int k) {
        this.graph = new HashMap<>();
        this.k = k;
    }

    public void addEdge(int src, int dest, double weight) {
        graph.putIfAbsent(src, new ArrayList<>());
        graph.get(src).add(new Edge(dest, weight));
    }

    public List<Path> findKShortestPaths(int source, int destination, double q) {
        PriorityQueue<Path> pq = new PriorityQueue<>();
        List<Path> kShortestPaths = new ArrayList<>();
        pq.add(new Path(Arrays.asList(source), 0));

        while (!pq.isEmpty() && kShortestPaths.size() < k) {
            Path currentPath = pq.poll();
            int lastNode = currentPath.nodes.get(currentPath.nodes.size() - 1);

            if (lastNode == destination) {
                kShortestPaths.add(currentPath);
                continue;
            }

            for (Edge edge : graph.getOrDefault(lastNode, new ArrayList<>())) {
                if (!currentPath.nodes.contains(edge.target)) {
                    List<Integer> newPathNodes = new ArrayList<>(currentPath.nodes);
                    newPathNodes.add(edge.target);
                    double newCost = currentPath.cost + edge.weight * q; // Adjust cost based on q
                    pq.add(new Path(newPathNodes, newCost));
                }
            }
        }
        return kShortestPaths;
    }

    public static void main(String[] args) {
        KShortestPaths ksp = new KShortestPaths(3);

        // Graph input (Example)
        ksp.addEdge(1, 2, 2);
        ksp.addEdge(1, 3, 4);
        ksp.addEdge(2, 3, 1);
        ksp.addEdge(2, 4, 7);
        ksp.addEdge(3, 4, 3);

        int source = 1, destination = 4;
        double q = 0.8;  // q should be between 0 and 1

        List<Path> paths = ksp.findKShortestPaths(source, destination, q);

        System.out.println("Top 3 shortest paths:");
        for (Path path : paths) {
            System.out.println("Path: " + path.nodes + " | Cost: " + path.cost);
        }
    }
}
