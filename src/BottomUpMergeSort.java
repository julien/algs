public class BottomUpMergeSort {

	private static Comparable[] aux;

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i-1])) return false;
		return true;
	}

	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid+1, hi);

		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		int i = lo, j = mid + 1;

		for (int k = lo; k <= hi; k++) {
			if      (i > mid)              a[k] = aux[j++];
			else if (j > hi)               a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else                           a[k] = aux[i++];
		}

		assert isSorted(a, lo, hi);
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		if (!less(a[mid+1], a[mid])) return;
		merge(a, lo, mid, hi);
	}

	public static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz = sz+sz)
			for (int lo = 0; lo < N-sz; lo += sz+sz)
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
	}

	public static void main(String[] args) {
		Comparable[] a = {'H', 'E', 'L', 'L', 'O', 'W', 'O', 'R', 'L', 'D'};
		sort(a);
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
}