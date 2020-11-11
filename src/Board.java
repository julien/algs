import java.util.LinkedList;
import java.util.List;

public class Board {
    private final int[][] blocks;
	private final int n;
	private int blankRow;
	private int blankCol;

    public Board(int[][] blocks) {
        if (blocks == null)
            throw new NullPointerException();

        this.blocks = copy(blocks);
		n = blocks.length;

		System.out.println("hey" + n);

		blankRow = -1;
		blankCol = -1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] == 0) {
					blankRow = i;
					blankCol = j;
					return;
				}
				System.out.println(blocks[i][j]);
			}
		}
    }


    public int dimension() {
        return n;
    }

    public int hamming() {
        int num = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == blankRow && j == blankCol)
					continue;

				int val = blocks[i][j] - 1;
				int row = val / n;
				int col = val % n;
				int res = Math.abs(row - i) + Math.abs(col - j);

				if (res != 0) {
					num++;
				}
			}
		}

        return num;
    }

    public int manhattan() {
        int dist = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == blankRow && j == blankCol)
					continue;

				int val = blocks[i][j] - 1;
				int row = val / n;
				int col = val % n;

				dist += Math.abs(row - i) + Math.abs(col - j);
			}
		}

        return dist;
    }

    public boolean isGoal() {
		return hamming() == 0;
    }

    public Board twin() {
		int[][] clone = copy(blocks);

		int tmp;
		if (blankRow != 0) {
			tmp = clone[0][0];
			clone[0][0] = clone[0][1];
			clone[0][1] = tmp;
		} else {
			tmp = clone[1][0];
			clone[1][0] = clone[1][1];
			clone[1][1] = tmp;
		}

		return new Board(clone);
    }

    public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;

		Board b = (Board) y;

		if (b.blankRow != blankRow) return false;
		if (b.blankCol != blankCol) return false;
		if (b.n != n) return false;

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (b.blocks[i][j] != blocks[i][j]) return false;

		return true;
    }

    public Iterable<Board> neighbors() {
		List<Board> neighbors = new LinkedList<>();

		int tmp;
		int[][] clone;

		if (blankRow > 0) {
			clone = copy(blocks);
			tmp = clone[blankRow][blankCol];
			clone[blankRow][blankCol] = clone[blankRow - 1][blankCol];
			clone[blankRow - 1][blankCol] = tmp;

			neighbors.add(new Board(clone));
		}
		if (blankRow < n -1) {
			clone = copy(blocks);
			tmp = clone[blankRow][blankCol];
			clone[blankRow][blankCol] = clone[blankRow + 1][blankCol];
			clone[blankRow + 1][blankCol] = tmp;

			neighbors.add(new Board(clone));
		}
		if (blankCol > 0) {
			clone = copy(blocks);
			tmp = clone[blankRow][blankCol];
			clone[blankRow][blankCol] = clone[blankRow][blankCol - 1];
			clone[blankRow][blankCol - 1] = tmp;

			neighbors.add(new Board(clone));
		}
		if (blankCol < - 1) {
			clone = copy(blocks);
			tmp = clone[blankRow][blankCol];
			clone[blankRow][blankCol] = clone[blankRow][blankCol + 1];
			clone[blankRow][blankCol + 1] = tmp;

			neighbors.add(new Board(clone));
		}

		return neighbors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(System.lineSeparator());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(String.format("%2d ", blocks[i][j]));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

	private int[][] copy(int[][] src) {
		int[][] dst = new int[src.length][];
		for (int i = 0; i < src.length; i++)
			dst[i] = src[i].clone();
		return dst;
	}

	public static void main(String[] args) {}
}
