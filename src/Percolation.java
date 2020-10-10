import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public final class Percolation {
	private boolean[][] grid;
	private int gridSize;
	private int openSites;
	private WeightedQuickUnionUF wquGrid;
	private WeightedQuickUnionUF wquFull;
	private int virtualTop;
	private int virtualBottom;

	public Percolation(int n, int trials) {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be > 0");
		}

		if (trials <= 0) {
			throw new IllegalArgumentException("trials must be > 0");
		}

		gridSize = n;

		int gridSizeSquared = n * n;

		grid = new boolean[gridSize][gridSize];
		wquGrid = new WeightedQuickUnionUF(gridSizeSquared + 2);
		wquFull = new WeightedQuickUnionUF(gridSizeSquared + 1);
		virtualTop = gridSizeSquared;
		virtualBottom = gridSizeSquared + 1;
		openSites = 0;
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
		openSites++;

		if (row == 1) {
			wquGrid.union(virtualTop, index);
			wquFull.union(virtualTop, index);
		}

		if (row == gridSize) {
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
		return wquFull.find(virtualTop) != 0 && wquFull.find(mapToGrid(row, col) - 1) != 0;
	}

	public int numberOfOpenSites() {
		return openSites;
	}

	public boolean percolates() {
		return wquGrid.find(virtualTop) != 0 && wquGrid.find(virtualBottom) != 0;
	}

	private boolean isInBounds(int row, int col) {
		int r = row - 1;
		int c = col - 1;

		return (r >= 0 && c >= 0 && r < gridSize && c < gridSize);
	}

	private int mapToGrid(int row, int col) {
		return gridSize * (row - 1) + col;
	}

	private void validate(int row, int col) {
		if (!isInBounds(row, col)) {
			throw new IllegalArgumentException(
					"Index out of bounds for row: " + row + ", column: " + col);
		}
	}

	public static void main(String args[]) {
		if (args.length < 2) {
			return;
		}

		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		Percolation percolation = new Percolation(n, t);
	}
}
