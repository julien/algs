import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] grid;
	private int size;
	private int open;
	private WeightedQuickUnionUF wquGrid;
	private WeightedQuickUnionUF wquFull;
	private int virtualTop;
	private int virtualBottom;

	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be > 0");
		}

		size = n;

		int sizeSquared = n * n;

		grid = new boolean[size][size];
		wquGrid = new WeightedQuickUnionUF(sizeSquared + 2);
		wquFull = new WeightedQuickUnionUF(sizeSquared + 1);
		virtualTop = sizeSquared;
		virtualBottom = sizeSquared + 1;
		open = 0;
	}

	public void open(int row, int col) {
		validate(row, col);

		if (isOpen(row,  col)) {
			return;
		}

		int r = row - 1;
		int c = col - 1;

		int index = mapToGrid(row, col) - 1;

		grid[r][c] = true;
		open++;

		if (row == 1) {
			wquGrid.union(virtualTop, index);
			wquFull.union(virtualTop, index);
		}

		if (row == size) {
			wquGrid.union(virtualBottom, index);
		}

		if (isInBounds(row, col - 1) && isOpen(row, col - 1)) {
			wquGrid.union(index, mapToGrid(row, col - 1) - 1);
			wquFull.union(index, mapToGrid(row, col - 1) - 1);
		}

		if (isInBounds(row, col + 1) && isOpen(row, col + 1)) {
			wquGrid.union(index, mapToGrid(row, col + 1) - 1);
			wquFull.union(index, mapToGrid(row, col + 1) - 1);
		}

		if (isInBounds(row - 1, col) && isOpen(row - 1, col)) {
			wquGrid.union(index, mapToGrid(row - 1, col) - 1);
			wquFull.union(index, mapToGrid(row - 1, col) - 1);
		}

		if (isInBounds(row + 1, col) && isOpen(row + 1, col)) {
			wquGrid.union(index, mapToGrid(row + 1, col) - 1);
			wquFull.union(index, mapToGrid(row + 1, col) - 1);
		}
	}

	public boolean isOpen(int row, int col) {
		validate(row, col);
		return grid[row - 1][col - 1];
	}

	public boolean isFull(int row, int col) {
		validate(row, col);
		return wquFull.find(virtualTop) == wquFull.find(mapToGrid(row, col) - 1);
	}

	public int numberOfOpenSites() {
		return open;
	}

	public boolean percolates() {
		return wquGrid.find(virtualTop) == wquGrid.find(virtualBottom);
	}

	private boolean isInBounds(int row, int col) {
		int r = row - 1;
		int c = col - 1;

		return (r >= 0 && c >= 0 && r < size && c < size);
	}

	private int mapToGrid(int row, int col) {
		return size * (row - 1) + col;
	}

	private void validate(int row, int col) {
		if (!isInBounds(row, col)) {
			throw new IllegalArgumentException(
					"Index out of bounds for row: " + row + ", column: " + col);
		}
	}

	public static void main(String args[]) {
		int size = Integer.parseInt(args[0]);

		Percolation percolation = new Percolation(size);

		for (int i = 1; i < args.length; i += 2) {
			int row = Integer.parseInt(args[i]);
			int col = Integer.parseInt(args[i + 1]);

			percolation.open(row, col);

			if (percolation.percolates()) {
				StdOut.printf("The System percolates");
			}
		}
	}
}
