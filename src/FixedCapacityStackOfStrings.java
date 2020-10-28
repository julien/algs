import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings {

    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    void push(String item) {
        s[N++] = item;
    }

    String pop() {
        // Should throw an exception if stack is empty

        // return s[--N];

        // Avoids loitering
        String item = s[--N];
        s[N] = null;
        return item;
    }

    boolean isEmpty() {
        return N == 0;
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(100);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop() + " ");
            else stack.push(s);
        }
        StdOut.println("");
    }
}
