import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int count;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null is not a valid argument");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        count++;
        if (count == 1) {
            last = first;
        }
        else {
            oldFirst.previous = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null is not a valid argument");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        count++;
        if (count == 1) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        first = first.next;
        count--;
        if (count == 1) {
            last = first;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        last = last.previous;
        count--;
        if (count == 1) {
            first = last;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public Item next() {
            if (current.equals(last)) {
                throw new java.util.NoSuchElementException("no more items to return");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> ds = new Deque<Integer>();
        for (int i = 1; i <= 10; i++) {
            ds.addFirst(i);
        }
        System.out.println(ds.count);
        for (int i = 0; i < 5; i++) {
            System.out.println(ds.removeFirst());
            System.out.println(ds.count);
        }
        System.out.println(ds.count);
        for (int i = 0; i < 5; i++) {
            System.out.println(ds.removeLast());
        }
        System.out.println(ds.count);

    }
}
