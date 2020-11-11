import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

import java.util.Deque;
import java.util.LinkedList;

public class Solver {
	private boolean solvable;
	private MinPQ<SearchNode> minPQ;
	private SearchNode solutionNode;

	public Solver(Board initial) {
		solutionNode = null;

		minPQ = new MinPQ<>();
		minPQ.insert(new SearchNode(initial, 0, null));

		while (true) {
			SearchNode node = minPQ.delMin();
			Board board = node.getBoard();

			if (board.isGoal()) {
				solvable = true;
				solutionNode = node;
				break;
			}
			if (board.hamming() == 2 && board.twin().isGoal()) {
				solvable = false;
				break;
			}

			int moves = node.getMoves();
			Board prev = moves > 0 ? node.getPrev().getBoard() : null;

			for (Board next : board.neighbors()) {
				if (prev != null && next.equals(prev))
					continue;

				minPQ.insert(new SearchNode(next, moves + 1, node));
			}
		}
	}

	private class SearchNode implements Comparable<SearchNode> {
		private final SearchNode prev;
		private final Board board;
		private final int moves;

		SearchNode(Board board, int moves, SearchNode prev) {
			this.board = board;
			this.moves = moves;
			this.prev = prev;
		}

		@Override
		public int compareTo(SearchNode that) {
			return this.priority() - that.priority();
		}

		public int priority() {
			return board.manhattan() + moves;
		}

		public Board getBoard() { return board; }
		public int getMoves() { return moves; }
		public SearchNode getPrev() { return prev; }
	}

	public boolean isSolvable() { return solvable; }

	public int moves() {
		return isSolvable() ? solutionNode.getMoves() : -1;
	}

	public Iterable<Board> solution() {
		if (!isSolvable()) return null;

		Deque<Board> solution = new LinkedList<>();
		SearchNode node = solutionNode;
		while (node != null) {
			solution.addFirst(node.getBoard());
			node = node.getPrev();
		}
		return solution;
	}

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();

        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
