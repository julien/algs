public class FixedCapacityStack<Item> {
	private Item[] s;
	private int N = 0;

	public FixedCapacityStack(int capacity) {
		// Will print a warning
		s = (Item[]) new Object[capacity];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(Item item) {
		s[N++] = item;
	}

	public Item pop() {
		return s[--N];
	}
}
