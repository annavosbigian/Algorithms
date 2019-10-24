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
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        count++;
        if (count == 1) {
            Node oldLast = last;
            last = first;
        }
        else {
            oldfirst.previous = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null is not a valid argument");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        count++;
        if (count == 1) {
            Node oldFirst = first;
            first = last;
            first.next = oldFirst;
        }
        else {
            oldlast.next = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        if (count == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
        }
        count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        if (count == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.previous;
        }
        count--;
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
            System.out.println(item);
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
            ds.addLast(i);
        }
        System.out.println(ds.size());
        System.out.println("removing last " + ds.removeLast());
        System.out.println("removing last " + ds.removeLast());
        System.out.println("removing last " + ds.removeLast());
        System.out.println("removing first " + ds.removeFirst());
        System.out.println("removing first " + ds.removeFirst());
        ds.addFirst(0);
        ds.addLast(11);
        System.out.println("removing last " + ds.removeLast());
        System.out.println("removing last " + ds.removeLast());
        System.out.println("removing last " + ds.removeLast());
        System.out.println(ds.size());
    }
}
