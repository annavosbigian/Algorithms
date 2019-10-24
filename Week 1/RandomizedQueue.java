/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        count = 0;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null is not a valid argument");
        }
        count++;
        if (count == queue.length) {
            resize(2 * queue.length);
        }
        queue[count] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        int i = StdRandom.uniform(0, count);
        Item item = queue[i];
        for (int j = i; j < count; j++) {
            queue[j] = queue[j + 1];
        }
        count--;
        if (count > 0 && count == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        int random = StdRandom.uniform(0, count);
        Item item = queue[random];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int random;
        private int current;
        private Item[] q2;

        public ArrayIterator() {
            current = count - 1;
            q2 = (Item[]) new Object[count];
            for (int i = 0; i < count; i++) {
                q2[i] = queue[i];
            }
            StdRandom.shuffle(q2);
        }

        public boolean hasNext() {
            return current > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("no more items to return");
            }
            return q2[current--];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 10; i++) {
            rq.enqueue(i);
        }
        System.out.println(rq.size());
        System.out.println(rq.sample());
        System.out.println("removing " + rq.dequeue());
        System.out.println("removing " + rq.dequeue());
        System.out.println(rq.sample());
        System.out.println(rq.size());
        Iterator<Integer> iterator = rq.iterator();
        for (int i : rq) {
            StdOut.println("iterating " + iterator.next());
        }
    }
}

