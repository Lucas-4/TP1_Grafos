import graph.*;
import java.util.Random;
import java.time.*;

public class App {
    public static void main(String[] args) throws Exception {
        int numV = 1000;
        Graph g = new Graph(numV, false, true);
        Random r = new Random();

        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                if (i != j) {
                    g.addAresta(i, j, r.nextInt(1000));
                }
            }
        }

        // g.printGraph();
        Instant start = Instant.now();
        g.dijkstra(0);
        g.bellman_ford(0);
        g.floyd_warshall();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis() + "ms");

    }
}
