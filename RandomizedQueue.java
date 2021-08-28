/* *****************************************************************************
 *  Name:   José Medardo Tapia Téllez
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rq;
    private int nItems;

    // construct Randomized Queue
    public RandomizedQueue() {
        rq = (Item[]) new Object[1];
        nItems = 0;
    }

    // is the randomized queue empyt?
    public boolean isEmpty() {
        return nItems == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return nItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (nItems == rq.length) resize(2 * rq.length);
        rq[nItems++] = item;
    }

    //  remove and return a random item
    public Item dequeue() {
        // Corner case if Randomized Queue is empty
        if (nItems == 0) throw new NoSuchElementException();
        // Select random position and item
        int rPosition = StdRandom.uniform(nItems);
        Item rItem = rq[rPosition];
        // If it is different to last one, swap it (Doesn't affect probability)
        if (rPosition != (nItems - 1)) {
            rq[rPosition] = rq[nItems - 1];
        }
        rq[nItems - 1] = null; //   Get rid of it
        nItems--;
        //  Check for array size
        if (nItems > 0 && nItems == rq.length / 4) resize(rq.length / 2);
        return rItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        //  Corner case when randomized queue is empty
        if (nItems == 0) throw new NoSuchElementException();
        // Select random position and item
        int rPosition = StdRandom.uniform(nItems);
        Item rItem = rq[rPosition];
        return rItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // Private Methods
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < nItems; i++) {
            copy[i] = rq[i];
        }
        rq = copy;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] rsq;
        private int index;

        public RandomizedQueueIterator() {
            index = 0;
            rsq = (Item[]) new Object[nItems];
            for (int i = 0; i < nItems; i++) {
                rsq[i] = rq[i];
            }
            StdRandom.shuffle(rsq);
        }

        public boolean hasNext() {
            return index < nItems;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            // StdOut.println("current: " + rsq[index]);
            if (!hasNext()) throw new NoSuchElementException();

            Item item = rsq[index];
            index++;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        //  Checking isEmpty() method
        StdOut.println("Is the randomized queue empty?: " + rq.isEmpty());
        // Checking size() method
        StdOut.println("The size of an empty RQ is: " + rq.size());
        // Checking enqueue() method (Add four items)
        rq.enqueue("Hola");
        rq.enqueue("me");
        rq.enqueue("llamo");
        rq.enqueue("Jose.");
        // Checking iterator and size method
        StdOut.println("Is the RQ empty after adding elements? " + rq.isEmpty());
        StdOut.println("The size after adding four items is: " + rq.size());
        for (String i : rq) {
            StdOut.println(i);
        }
        // Checking sample (Obtain a random element)
        StdOut.println("Sampled element: " + rq.sample());
        // Checking dequeue (Get an element and remove it)
        StdOut.println("Eliminated element: " + rq.dequeue());
        for (String i : rq) {
            StdOut.println(i);
        }

    }

}
