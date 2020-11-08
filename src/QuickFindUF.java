import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        // (2N + 2 array access) - quadratic
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = qid;
    }

    void print() {
        for (int i = 0; i < id.length; i++)
            System.out.print(id[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickFindUF uf  = new QuickFindUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        uf.print();
    }
}
