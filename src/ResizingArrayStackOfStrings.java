import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStackOfStrings {

    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings(int capacity) {
        s = new String[1];
    }

    void push(String item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    String pop() {
        // Half the size of the array when it's
        // one-quarter full
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }

    boolean isEmpty() {
        return N == 0;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }

    public static void main(String[] args) {
        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings(100);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop() + " ");
            else stack.push(s);
        }
        StdOut.println("");
    }
}
