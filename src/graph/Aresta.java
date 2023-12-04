package graph;

public class Aresta {
	public int s, d;
	public float peso;

	public Aresta(int s, int d, float peso) {
		this.peso = peso;
		this.s = s;
		this.d = d;
	}

	public Aresta(int s, int d) {
		this.s = s;
		this.d = d;
	}
}
