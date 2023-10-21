package graph;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int numVertices;
    // matriz de adjacência
    private Aresta[][] matriz;
    private boolean isDirected;
    private boolean isWeighted;
    private int numArestas = 0;

    // construtor que recebe o número de vértices do grafo
    public Graph(int n, boolean isDirected, boolean isWeighted) {
        this.numVertices = n;
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        matriz = new Aresta[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = null;
            }
        }
    }

    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println("");
            for (int j = 0; j < numVertices; j++) {
                if (matriz[i][j] == null) {
                    System.out.print(" X ");
                } else if (isWeighted) {
                    System.out.printf(" %.1f ", matriz[i][j].peso);
                } else {
                    System.out.print(" 1 ");
                }
            }
        }
    }

    /**
     * Função para adicionar uma aresta ao grafo
     * 
     * Se o grafo for direcionado, a aresta será criada como saindo de v1 e entrando
     * em v2
     * 
     * @param v1 primeiro vértice da aresta
     * @param v2 segundo vértice da aresta.
     */
    public void addAresta(int v1, int v2) throws InvalidOperationEx {
        if (matriz[v1][v2] != null)
            return;
        if (isWeighted) {
            throw new InvalidOperationEx(
                    "The graph is weighted, to create an edge you must pass the third argument 'peso'");
        }
        if (isDirected) {
            matriz[v1][v2] = new Aresta();
        } else {
            matriz[v1][v2] = new Aresta();
            matriz[v2][v1] = new Aresta();
        }
        numArestas++;
    }

    /**
     * Função para adicionar uma aresta ao grafo
     * 
     * @param v1   primeiro vértice da aresta
     * @param v2   segundo vértice da aresta.
     * @param peso peso da aresta
     * @throws InvalidOperationEx
     */
    public void addAresta(int v1, int v2, float peso) throws InvalidOperationEx {
        if (matriz[v1][v2] != null)
            return;
        if (!isWeighted) {
            throw new InvalidOperationEx(
                    "The graph is unweighted, to create an edge you must remove the third argument 'peso'");
        }
        if (isDirected) {
            matriz[v1][v2] = new Aresta(peso);

        } else {
            matriz[v1][v2] = new Aresta(peso);
            matriz[v2][v1] = new Aresta(peso);
        }
        numArestas++;
    }

    /**
     * Função para deletar uma aresta entre dois vértices
     * 
     * @param v1
     * @param v2
     * 
     */
    public void deleteAresta(int v1, int v2) throws Exception {
        if (matriz[v1][v2] != null)
            return;
        if (isDirected) {
            matriz[v1][v2] = null;
        } else {
            matriz[v1][v2] = null;
            matriz[v2][v1] = null;
        }
        numArestas--;
    }

    public int grauVertice(int v) throws InvalidOperationEx {
        if (isDirected) {
            throw new InvalidOperationEx(
                    "Can't use this function in a directed graph, use grauVerticeIn or grauVerticeOut");
        }
        int grau = 0;
        for (int i = 0; i < numVertices; i++) {
            if (matriz[v][i] != null) {
                grau++;
            }
        }
        System.out.println(grau);
        return grau;
    }

    public int grauVerticeIn(int v) throws InvalidOperationEx {
        if (!isDirected) {
            throw new InvalidOperationEx("Can't use this function in an undirected graph, use grauVertice instead");
        }
        int grau = 0;
        for (int i = 0; i < numVertices; i++) {
            if (matriz[i][v] != null) {
                grau++;
            }
        }
        System.out.println(grau);

        return grau;
    }

    public int grauVerticeOut(int v) throws Exception {
        if (!isDirected) {
            throw new InvalidOperationEx("Can't use this function in an undirected graph, use grauVertice instead");
        }
        int grau = 0;
        for (int i = 0; i < numVertices; i++) {
            if (matriz[v][i] != null) {
                grau++;
            }
        }
        System.out.println(grau);

        return grau;
    }

    public int grau() {
        System.out.println(numArestas * 2);
        return numArestas * 2;
    }

    // função que retorna os vizinhos de um vértice
    public ArrayList<Integer> vizinhos(int v) {
        ArrayList<Integer> vizinhos = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (matriz[v][i] != null) {
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }

    // função que retorna os vizinhos sucessores de um vértice em um grafo
    // direcionado
    public ArrayList<Integer> sucessores(int v) throws Exception {
        if (!isDirected)
            throw new InvalidOperationEx("Can't use this function in an undirected graph");

        ArrayList<Integer> sucessores = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (matriz[v][i] != null) {
                sucessores.add(i);
            }
        }
        return sucessores;
    }

    // função que retorna os vizinhos antecessores de um vértice em um grafo
    // direcionado
    public ArrayList<Integer> antecessores(int v) throws Exception {
        if (!isDirected) {
            throw new InvalidOperationEx("Can't use this function in an undirected graph");
        }

        ArrayList<Integer> antecessores = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (matriz[i][v] != null) {
                antecessores.add(i);
            }
        }
        return antecessores;
    }

    // variables for breadth-first search
    private int t;
    private Queue<Integer> q;
    private int L[];
    private int nivel[];
    private Integer pai[];

    public void bfs(int r) {
        q = new LinkedList<>();
        L = new int[numVertices];
        nivel = new int[numVertices];
        pai = new Integer[numVertices];
        int v;
        t = 0;
        for (int i = 0; i < numVertices; i++) {
            L[i] = 0;
            nivel[i] = 0;
            pai[i] = null;
        }
        q.add(r);
        while (!q.isEmpty()) {
            v = q.poll();
            for (Integer w : vizinhos(v)) {
                if (L[w] == 0) {
                    pai[w] = v;
                    nivel[w] = nivel[v] + 1;
                    t++;
                    L[w] = t;
                    q.add(w);
                }
            }
        }
    }

    public boolean hasPath(int s, int d) {
        boolean hasPath = false;
        q = new LinkedList<>();
        L = new int[numVertices];
        nivel = new int[numVertices];
        pai = new Integer[numVertices];
        int v;
        t = 0;
        for (int i = 0; i < numVertices; i++) {
            L[i] = 0;
            nivel[i] = 0;
            pai[i] = null;
        }
        q.add(s);
        while (!q.isEmpty()) {
            v = q.poll();
            for (Integer w : vizinhos(v)) {
                if (L[w] == 0) {
                    pai[w] = v;
                    nivel[w] = nivel[v] + 1;
                    t++;
                    L[w] = t;
                    q.add(w);
                    if (d == w) {
                        hasPath = true;
                    }
                }
            }
        }
        return hasPath;
    }

    public void printPath(int s, int d) {
        if (!hasPath(s, d))
            return;
        ArrayList<Integer> path = new ArrayList<>();
        path.add(d);
        while (true) {
            d = pai[d];
            path.add(d);
            if (s == d) {
                break;
            }
        }
        Collections.reverse(path);
        System.out.printf("\n\n");

        for (int i = 0; i < path.size(); i++) {
            if (i != 0) {
                System.out.print(" -> ");
            }
            System.out.print(i);

        }
    }

    private int TD[];
    private int TT[];

    public void dfs(int r) {
        TD = new int[numVertices];
        TT = new int[numVertices];
        pai = new Integer[numVertices];
        t = 0;
        for (int i = 0; i < numVertices; i++) {
            TD[i] = 0;
            TT[i] = 0;
            pai[i] = null;
        }
        for (int i = 0; i < numVertices; i++) {
            if (TD[i] == 0) {
                dfsVisit(i);
            }
        }
    }

    private void dfsVisit(int v) {
        System.out.println(v);
        t++;
        TD[v] = t;
        for (Integer w : vizinhos(v)) {
            if (TD[w] == 0) {
                pai[w] = v;
                dfsVisit(w);
            }
        }
        t++;
        TT[v] = t;
    }

    public boolean isConexo() {
        bfs(0);
        boolean isConexo = true;
        for (int i = 0; i < L.length; i++) {
            if (L[i] == 0) {
                isConexo = false;
            }
        }
        return isConexo;
    }

    public boolean isRegular() throws Exception {
        boolean isRegular = true;
        for (int i = 1; i < numVertices; i++) {
            if (grauVertice(i - 1) != grauVertice(i)) {
                isRegular = false;
            }
        }
        return isRegular;
    }

    public boolean isCompleto() throws Exception {
        if (isRegular() && grauVertice(0) == numVertices - 1) {
            return true;
        }
        return false;
    }

    public void export(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("nodedef> name VARCHAR,label VARCHAR\n");
        for (int i = 0; i < numVertices; i++) {
            printWriter.printf("%d,v%d\n", i, i);
        }
        printWriter.print("edgedef> node1,node2,weight DOUBLE,directed BOOLEAN\n");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                float peso;
                if (isWeighted) {
                    peso = matriz[i][j].peso;
                } else {
                    peso = 1;
                }
                if (isDirected) {

                    if (matriz[i][j] != null) {
                        printWriter.printf("%d,%d,%f,%s\n", i, j, peso, isDirected ? "true" : "false");
                    }
                } else {
                    if (j >= i && matriz[i][j] != null) {
                        printWriter.printf("%d,%d,%f,%s\n", i, j, peso, isDirected ? "true" : "false");
                    }
                }

            }
        }
        printWriter.close();
    }
}
