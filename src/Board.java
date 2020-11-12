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
        blankRow = -1;
        blankCol = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    return;
                }
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
                if (i == blankRow && j == blankCol) {
                    continue;
                }

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

        Board that = (Board) y;
        if (this.blankCol != that.blankCol) return false;
        if (this.blankRow != that.blankRow) return false;
        if (this.n != that.n) return false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (this.blocks[i][j] != that.blocks[i][j]) return false;
        return true;
    }

    private int[][] copy(int[][] src) {
        int[][] dst = new int[src.length][];
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i].clone();
        }
        return dst;
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();

        int tmp;
        if (blankRow > 0) {
            int[][] up = copy(blocks);
            tmp = up[blankRow][blankCol];
            up[blankRow][blankCol] = up[blankRow - 1][blankCol];
            up[blankRow - 1][blankCol] = tmp;

            neighbors.add(new Board(up));
        }
        if (blankRow < n - 1) {
            int[][] down = copy(blocks);
            tmp = down[blankRow][blankCol];
            down[blankRow][blankCol] = down[blankRow + 1][blankCol];
            down[blankRow + 1][blankCol] = tmp;

            neighbors.add(new Board(down));
        }
        if (blankCol > 0) {
            int[][] left = copy(blocks);
            tmp = left[blankRow][blankCol];
            left[blankRow][blankCol] = left[blankRow][blankCol - 1];
            left[blankRow][blankCol - 1] = tmp;

            neighbors.add(new Board(left));
        }
        if (blankCol < n - 1) {
            int[][] right = copy(blocks);
            tmp = right[blankRow][blankCol];
            right[blankRow][blankCol] = right[blankRow][blankCol + 1];
            right[blankRow][blankCol + 1] = tmp;

            neighbors.add(new Board(right));
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(n).append(System.lineSeparator());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                builder.append(String.format("%2d ", blocks[i][j]));
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    public static void main(String[] args) {}
}

