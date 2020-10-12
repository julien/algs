import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[] grid;
	private int size;
	private int open;
	private WeightedQuickUnionUF wquGrid;
	private WeightedQuickUnionUF wquFull;

	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be > 0");
		}

		size = n;

		int sizeSquared = n * n;

		grid = new boolean[sizeSquared + 2];
		wquGrid = new WeightedQuickUnionUF(sizeSquared + 2);
		wquFull = new WeightedQuickUnionUF(sizeSquared + 1);
		open = 0;
	}

	public void open(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size) {
			throw new IllegalArgumentException(
				"Index out of bounds for row: " + row + ", column: " + col);
		}

		if (isOpen(row,  col)) {
			return;
		}

		int index = mapToGrid(row, col);
		grid[index] = true;

		open++;

		if (row == 1) {
			wquGrid.union(index, 0);
			wquFull.union(index, 0);
		}
		if (row == size) {
			wquGrid.union(index, size * size + 1);
		}

		if (row < size && isOpen(row + 1, col)) {
			wquGrid.union(index, mapToGrid(row + 1, col));
			wquFull.union(index, mapToGrid(row + 1, col));
		}
		if (row > 1 && isOpen(row - 1, col)) {
			wquGrid.union(index, mapToGrid(row - 1, col));
			wquFull.union(index, mapToGrid(row - 1, col));
		}
		if (col < size && isOpen(row, col + 1)) {
			wquGrid.union(index, mapToGrid(row, col + 1));
			wquFull.union(index, mapToGrid(row, col + 1));
		}
		if (col > 1 && isOpen(row, col - 1)) {
			wquGrid.union(index, mapToGrid(row, col - 1));
			wquFull.union(index, mapToGrid(row, col - 1));
		}
	}

	public boolean isOpen(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size) {
			throw new IllegalArgumentException(
				"Index out of bounds for row: " + row + ", column: " + col);
		}
		return grid[mapToGrid(row, col)];
	}

	public boolean isFull(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size) {
			throw new IllegalArgumentException(
				"Index out of bounds for row: " + row + ", column: " + col);
		}
		return wquFull.find(0) == wquFull.find(mapToGrid(row, col) - 1);
	}

	public int numberOfOpenSites() {
		return open;
	}

	public boolean percolates() {
		return wquGrid.find(0) == wquGrid.find(size * size + 1);
	}

	private int mapToGrid(int row, int col) {
		return size * (row - 1) + col;
	}

	public static void main(String args[]) {
		int size = Integer.parseInt(args[0]);

		Percolation percolation = new Percolation(size);

		for (int i = 1; i < args.length; i += 2) {
			int row = Integer.parseInt(args[i]);
			int col = Integer.parseInt(args[i + 1]);

			percolation.open(row, col);

			if (percolation.percolates()) {
				StdOut.printf("The System percolates %n");
			}
		}
	}
}
