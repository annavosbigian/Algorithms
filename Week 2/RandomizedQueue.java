/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        count = 0;
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
        if (count >= queue.length) {
            Item[] queue2 = (Item[]) new Object[count * 2];
            for (int i = 0; i < count; i++) {
                queue2[i] = queue[i];
            }
            queue = queue2;
        }
        queue[count] = item;
        count++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        int i = StdRandom.uniform(0, count);
        Item item = queue[i];
        queue[i] = queue[count - 1];
        queue[count - 1] = null;
        count--;
        if (count != 0 && count * 4 <= queue.length) {
            Item[] queue2 = (Item[]) new Object[count * 2];
            for (int j = 0; j < count; j++) {
                queue2[j] = queue[j];
            }
            queue = queue2;
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        int i = StdRandom.uniform(0, count);
        return queue[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int random;
        private int current;

        public ArrayIterator() {
            random = StdRandom.uniform(0, count);
            current = 0;
        }

        public boolean hasNext() {
            return current != count;
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("no more items to return");
            }
            current++;
            return queue[random];
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("I");
        rq.enqueue("was");
        rq.enqueue("a");
        rq.enqueue("tomato");
        System.out.println(rq.size());
        System.out.println(rq.sample());
        System.out.println("removing " + rq.dequeue());
        System.out.println("removing " + rq.dequeue());
        System.out.println(rq.sample());
        System.out.println(rq.size());
    }
}
