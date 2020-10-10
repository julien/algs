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
		wquGrid = new WeightedQuickUnionUF(gridSquared + 2);
		wquFull = new WeightedQuickUnionUF(gridSquared + 1);
		virtualTop = gridSquared;
		virtualBottom = qridSquared + 1;
		openSites = 0;
	}

	private boolean isInBounds(int row, int col) {
		int r = row - 1;
		int c = col - 1;

		return (r >= 0 && c >= 0 && r < gridSize && c < gridSize);
	}

	public boolean isOpen(int row, int col) {
		if (!isInBounds(row, col)) {
			throw new IllegalArgumentException(
					"Index out of bounds for row: " + row + ", column: " + col);
		}
		return grid[row][col];
	}

	public boolean isFull(int row, int col) {
		if (!isInBounds(row, col)) {
			throw new IllegalArgumentException(
					"Index out of bounds for row: " + row + ", column: " + col);
		}
		return wquFull.connected(virtualTop,
	}

	public int numberOfOpenSites() {
		return openSites;
	}

	public boolean percolates() {

	}

	public static void main(String args[]) {
		if (args.length < 2) {
			return;
		}

		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);

		Percolation p = new Percolation(n, t);
	}
}