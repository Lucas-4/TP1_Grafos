import graph.*;

public class App {
    public static void main(String[] args) throws Exception {
        Graph g = new Graph(5, false, false);
        g.addAresta(0, 1);
        g.addAresta(1, 2);
        g.addAresta(2, 3);
        g.addAresta(3, 4);
        g.printGraph();
        g.printPath(0, 2);
        g.export("graph.gdf");
    }
}
