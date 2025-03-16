public class PanEuropeanCost239 {

    public static void main(String[] args) {
        shortestPaths graph = new shortestPaths();
         //COST 239 GRAPH


        
        graph.addEdge(0, 1, 1310);
        graph.addEdge(1, 0, 1310);
        graph.addEdge(0, 2, 760);
        graph.addEdge(2, 0, 760);
        graph.addEdge(0, 6, 740);
        graph.addEdge(6, 0, 740);
        graph.addEdge(0, 3, 390);
        graph.addEdge(3, 0, 390);
        graph.addEdge(1, 7, 450);
        graph.addEdge(7, 1, 450);
        graph.addEdge(1, 4, 390);
        graph.addEdge(4, 1, 390);
        graph.addEdge(1, 2, 550);
        graph.addEdge(2, 1, 550);
        graph.addEdge(2, 4, 210);
        graph.addEdge(4, 2, 210);
        graph.addEdge(2, 3, 660);
        graph.addEdge(3, 2, 660);
        graph.addEdge(2, 5, 390);
        graph.addEdge(5, 2, 390);
        graph.addEdge(3, 7, 1090);
        graph.addEdge(7, 3, 1090);
        graph.addEdge(3, 6, 340);
        graph.addEdge(6, 3, 340);
        graph.addEdge(3, 9, 660);
        graph.addEdge(9, 3, 660);
        graph.addEdge(6, 5, 730);
        graph.addEdge(5, 6, 730);
        graph.addEdge(6, 9, 320);
        graph.addEdge(9, 6, 320);
        graph.addEdge(6, 8, 565);
        graph.addEdge(8, 6, 565);
        graph.addEdge(4, 7, 300);
        graph.addEdge(7, 4, 300);
        graph.addEdge(4, 10, 930);
        graph.addEdge(10, 4, 930);
        graph.addEdge(4, 5, 220);
        graph.addEdge(5, 4, 220);
        graph.addEdge(7, 10, 820);
        graph.addEdge(10, 7, 820);
        graph.addEdge(10, 8, 320);
        graph.addEdge(8, 10, 320);
        graph.addEdge(7, 8, 600);
        graph.addEdge(8, 7, 600);
        graph.addEdge(7, 5, 400);
        graph.addEdge(5, 7, 400);
        graph.addEdge(9, 10, 820);
        graph.addEdge(10, 9, 820);
        graph.addEdge(5, 8, 350);
        graph.addEdge(8, 5, 350);
        graph.addEdge(7, 8, 730);
        graph.addEdge(8, 7, 730);
        

        
       // graph.findthreeShortestPaths(0, 4);

        for(int i= 0; i< 11; i++){
            for (int j = 0;j<11;j++){
                if (i==j){
                    continue;
                }

                System.out.println("Source "+i+" Destination "+j);
                graph.findthreeShortestPaths(i, j);
                System.out.println("..............");
            }
        }
    }
}