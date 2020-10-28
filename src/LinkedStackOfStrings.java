import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStackOfStrings {

    private class Node {
        String item;
        Node next;
    }

    private Node first = null;

    void push(String item) {
        // Save link to the list
        Node oldfirst = first;
        // Create a new node for the beginning
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    String pop() {
        // Save item to return
        String item = first.item;
        // Delete the first node
        first = first.next;
        return item;
    }

    boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop() + " ");
            else stack.push(s);
        }
        StdOut.println("");
    }
}
