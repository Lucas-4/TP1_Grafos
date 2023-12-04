import graph.*;
import java.util.Random;
import java.time.*;

public class App {
    public static void main(String[] args) throws Exception {
        for (int numV = 10; numV <= 1000; numV *= 10) {

            Graph g = new Graph(numV, false, true);
            Random r = new Random();

            // create random graph
            for (int i = 0; i < numV; i++) {
                for (int j = 0; j < numV; j++) {
                    if (i != j) {
                        g.addAresta(i, j, r.nextInt(1000));
                    }
                }
            }

            Instant start, end;
            start = Instant.now();
            g.dijkstra(0);
            end = Instant.now();
            System.out.print("Execution duration for Dijkstra's algorithm in a " + numV + " vertex graph: ");
            System.out.println(Duration.between(start, end).toMillis() + "ms");

            start = Instant.now();
            g.bellman_ford(0);
            end = Instant.now();
            System.out.print("Execution duration for Bellman-Ford's algorithm in a " + numV + " vertex graph: ");
            System.out.println(Duration.between(start, end).toMillis() + "ms");

            start = Instant.now();
            g.floyd_warshall();
            end = Instant.now();
            System.out.print("Execution duration for Floyd-Warshall's algorithm in a " + numV + " vertex graph: ");
            System.out.println(Duration.between(start, end).toMillis() + "ms\n");

        }

    }
}
