/* *****************************************************************************
 *  Name: José Medardo Tapia Téllez
 *  Date: March 20th 2021
 *  Description: Generic data type for a Deque. The goal of this implementation
 *  is that of creating an elementary data structure using arrays and linked lists,
 *  generics and iterators.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    //  construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    //  is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    //  return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node incoming = new Node();
        incoming.item = item;

        if (size == 0) {
            last = incoming;
        }
        else if (size == 1) {
            incoming.next = last;
            last.prev = incoming;
        }
        else {
            incoming.next = first;
            first.prev = incoming;
        }
        first = incoming;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node incoming = new Node();
        incoming.item = item;
        if (size == 0) {
            first = incoming;
        }
        else if (size == 1) {
            incoming.prev = first;
            first.next = incoming;
        }
        else {
            incoming.prev = last;
            last.next = incoming;
        }
        last = incoming;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item toReturn = first.item;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            first.next.prev = null;
            first = first.next;
        }
        size--;
        return toReturn;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item toReturn = last.item;
        if (size == 1) {
            last = null;
            first = null;
        }
        else {
            last.prev.next = null;
            last = last.prev;
        }
        size--;
        return toReturn;
    }

    //  return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            // StdOut.println("current: " + current);
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> dq = new Deque<>();
        //  Check if the Deque is empty
        StdOut.println(dq.isEmpty());
        //  Add an item to the front
        dq.addFirst("Hola");
        //  Add an item to the back
        dq.addLast("mi");
        dq.addLast("nombre");
        dq.addLast("es");
        dq.addLast("Jose");
        //  Check iterator
        for (String i : dq) {
            StdOut.println(i);
        }
        // Checking removeFirst()
        dq.removeFirst();
        dq.removeLast();
        dq.removeLast();
        for (String i : dq) {
            StdOut.println(i);
        }
    }
}
