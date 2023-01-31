import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private ResizableArray<Item> q = new ResizableArray<Item>();

    // construct an empty randomized queue
    public RandomizedQueue() {}

    // is the randomized queue empty?
    public boolean isEmpty() {
        return q.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size(){
        return q.size();
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {throw new IllegalArgumentException(); }
        q.add(item);
    }

    // remove and return a random item
    public Item dequeue() {
        EmptyException();
        return q.remove(StdRandom.uniformInt(q.numOfElem));
    }

    // return a random item (but do not remove it)
    public Item sample() {
        EmptyException();
        return q.sample(StdRandom.uniformInt(q.numOfElem));
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new QueueIterator(); }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        for(int n: q){
            System.out.println(n);
        }
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        for(int n: q){
            System.out.println(n);
        }
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println("is empty: " + q.isEmpty());

    }

    private class QueueIterator implements Iterator<Item> {
        RandomizedQueue<Item> copy;
        
        QueueIterator() {
            copy = new RandomizedQueue<Item>();

            for(int i = 0; i < q.numOfElem; i ++){
                copy.enqueue(q.elements[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Item next() {
            if(isEmpty()) throw new NoSuchElementException();
            return copy.dequeue();
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
        
    }

    private class ResizableArray<Item> {
        int size;
        int numOfElem;
        Item elements[];
        
        public ResizableArray(){
            size = 1;
            numOfElem = 0;
            elements = (Item[]) new Object[size];
        }

        public boolean isEmpty() { return numOfElem == 0; }

        public int size() { return numOfElem; }

        public void add(Item item) {
            if(numOfElem >= size) {
                resize(2 * size);
                size *= 2;
            }
            elements[numOfElem] = item;
            numOfElem++;
        }

        public Item sample(int index) {return elements[index];}
        
        public Item remove(int index) {
            Item item = elements[index];
            elements[index] = elements[numOfElem - 1];
            elements[numOfElem - 1] = null;
            numOfElem--;

            if (numOfElem == size/4) resize(size/2);
            return item;
        }

        private void resize(int capacity) {
            if (isEmpty()) return;

            Item copy[] = (Item[]) new Object[capacity];
            for (int i = 0; i < numOfElem; i++) 
                copy[i] = elements[i];
            elements = copy; 
        }

    }

    private void EmptyException() {
        if(isEmpty()) throw new NoSuchElementException();
    }

}
