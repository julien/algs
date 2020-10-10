import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public final class PercolationStats {

	private int numTrials;
	private double[] results;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("N and T must be <= 0");
		}

		int gridSize = n;
		numTrials = trials;
		results = new double[numTrials];

		for (int i = 0; i < numTrials; i++) {
			Percolation percolation = new Percolation(gridSize);
			while (!percolation.percolates()) {
				int row = StdRandom.uniform(1, gridSize + 1);
				int col = StdRandom.uniform(1, gridSize + 1);
				percolation.open(row, col);
			}
			int openSites = percolation.numberOfOpenSites();
			double result = (double) openSites / (gridSize * gridSize);
			results[i] = result;
		}
	}

	public double mean() {
		return StdStats.mean(results);
	}

	public double stddev() {
		return StdStats.stddev(results);
	}

	public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(numTrials));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(numTrials));
    }

    public static void main(String[] args) {
        int gridSize = 10;
        int numTrials = 10;

        if (args.length >= 2) {
            gridSize = Integer.parseInt(args[0]);
            numTrials = Integer.parseInt(args[1]);
        }

        PercolationStats ps = new PercolationStats(gridSize, numTrials);
        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }

}
