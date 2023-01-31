import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {throw new IllegalArgumentException(); }
        Node newNode = new Node(item);
        size++;

        if(first == null){
            first = newNode;
            last = newNode;
        }
        else {
        newNode.next = first;
        first.previous = newNode;
        first = newNode;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {throw new IllegalArgumentException(); }
        Node newNode = new Node(item);
        size++;

        if(last == null){
            last = newNode;
            first = newNode;
        }
        else{
            newNode.previous = last;
            last.next = newNode;
            last = newNode;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        size--;

        Node node = first;
        first = first.next;
        if(isEmpty()) last = null;
        else first.previous = null;
        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(isEmpty()) throw new NoSuchElementException();
        size--;
        
        Node node = last;
        last = last.previous;
        if(isEmpty()) first = null;
        else last.next = null;
        return node.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {return new QueueIterator();}

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<>();
        StdOut.println(deck.isEmpty());

        for(int i = 0; i < 5; i++)
            deck.addFirst(i);
        
        for(int i = 9; i >= 5; i--)
            deck.addLast(i);

        StdOut.println(deck.removeFirst());
        StdOut.println(deck.removeFirst());
        StdOut.println(deck.isEmpty());
        StdOut.println(deck.size());

        for(int i : deck)
            StdOut.println(i);

    }

    //node class
    private class Node{
        Item item;
        Node next;
        Node previous;
        public Node(Item item){ 
            this.item = item; 
        }
    }


    private class QueueIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(isEmpty()) throw new NoSuchElementException();
            else if(current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
        
    }

}
