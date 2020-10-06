import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnion {
	private int[] id;
	private int[] sz;

	public WeightedQuickUnion(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	private int root(int i) {
		while (i != id[i]) {
			// Make every other node in path point to its grandparent
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}

	boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	void union(int p, int q) {
		int i = root(p);
		int j = root(q);

		if (i == j) return;

		if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
		else               { id[j] = i; sz[i] += sz[j]; }
	}

	void print() {
		System.out.print("id: [ ");
		for (int i = 0; i < id.length; i++) {
			if (i < id.length - 1)
				System.out.print(id[i] + ", ");
			else
				System.out.print(id[i]);
		}
		System.out.print(" ]");

		System.out.println("");

		System.out.print("sz: [ ");
		for (int i = 0; i < sz.length; i++) {
			if (i < sz.length - 1)
				System.out.print(sz[i] + ", ");
			else
				System.out.print(sz[i]);
		}
		System.out.print(" ]");
		System.out.println("");
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		WeightedQuickUnion uf  = new WeightedQuickUnion(N);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!uf.connected(p, q)) {
				uf.union(p, q);
				StdOut.println(p + " " + q);
			}
		}
		uf.print();
	}
}