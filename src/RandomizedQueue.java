import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a;
	private int n;

	public RandomizedQueue() {
		a = (Item[]) new Object[2];
		n = 0;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public void enqueue(Item item) {
		if (item == null) throw new NullPointerException();
		if (n == a.length) resize(2 * a.length);
		if (n == 0) {
			a[n++] = item;
			return;
		}
		int rnd = StdRandom.uniform(n);
		Item tmp = a[rnd];
		a[rnd] = item;
		a[n++] = tmp;
	}

	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException();
		if (n == a.length / 4) resize(a.length / 2);
		int rnd = StdRandom.uniform(n);
		Item item = a[rnd];
		a[rnd] = a[--n];
		a[n] = null;
		return item;
	}

	public Item sample() {
		if (isEmpty()) throw new NoSuchElementException();
		return a[StdRandom.uniform(n)];
	}

	@Override
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}

	private void resize(int capacity) {
		Item[] tmp = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++)
			tmp[i] = a[i];
		a = tmp;
	}

	private class ArrayIterator implements Iterator<Item> {
		private int i;
		private int[] indices;

		public ArrayIterator() {
			i = 0;
			indices = new int[n];
			for (int j = 0; j < n; j++) {
				indices[j] = j;
			}
			StdRandom.shuffle(indices);
		}

		@Override
		public boolean hasNext() {
			return i < n;
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return a[indices[i++]];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> queue = new RandomizedQueue<>();
		for (int i = 0; i < 100; i++)
			queue.enqueue(i * 2);

		System.out.println(queue.size());

		for (Integer i : queue)
			System.out.println(i);

		System.out.println("sample: " + queue.sample());

		while (!queue.isEmpty()) System.out.println(queue.dequeue());
		System.out.println(queue.size());
	}
}
