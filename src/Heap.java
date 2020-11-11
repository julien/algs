public class Heap {
	private int[] pq;
	private int N;

	public Heap(int capacity) {
		N = 0;
		pq = new int[capacity];
	}

	public void insert(Key x) {
		pq[++N] = x;
		swim(N);
	}

	private void sink(int k) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	private void swim(int k) {
		while (k > l && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}

	public Key delMax() {
		Key max = pq[1];
		exch(1, N--);
		sink(1);
		pq[N+1] = null;
		return max;
	}
}
