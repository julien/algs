import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public final class PercolationStats {
	private int trials;
	private double[] results;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("N and T must be <= 0");
		}

		this.trials = trials;
		results = new double[trials];

		for (int i = 0; i < trials; i++) {
			Percolation percolation = new Percolation(n);
			while (!percolation.percolates()) {
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);
				percolation.open(row, col);
			}
			int openSites = percolation.numberOfOpenSites();
			double result = (double) openSites / (n * n);
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
		return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
	}

	public double confidenceHi() {
		return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
	}

	public static void main(String[] args) {
		int n = 10;
		int trials = 10;

		if (args.length >= 2) {
			n = Integer.parseInt(args[0]);
			trials = Integer.parseInt(args[1]);
		}

		PercolationStats ps = new PercolationStats(n, trials);
		String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
		StdOut.println("mean = %d" + ps.mean());
		StdOut.println("stddev = " + ps.stddev());
		StdOut.println("95% confidence interval = " + confidence);
	}
}
