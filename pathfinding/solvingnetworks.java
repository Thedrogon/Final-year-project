
package pathfinding;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays; 


class GraphLoader {
    public ArrayList<int[]> loadGraph(String filename)throws IOException {
        ArrayList<int[]> graph = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int source = Integer.parseInt(parts[0]);
                int destination = Integer.parseInt(parts[1]);
                int dist = Integer.parseInt(parts[2]);
                graph.add(new int[]{source, destination,dist});
                graph.add(new int[]{destination, source,dist});
            }
        }
        return graph;
    }
}

public class solvingnetworks {
    public static void main(String[] args) {
        GraphLoader hello=new GraphLoader();
        ArrayList<int[]> bro=new ArrayList<>();

    try {
        bro=hello.loadGraph("COST239.txt");
    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.println(bro.get(0)[2]);
    }
}
