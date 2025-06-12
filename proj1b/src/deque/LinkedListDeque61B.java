package deque;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>, Iterable<T>{
    private Node sentinel;
    private int size;

    /**
     * Node the nested class
     */
    public class Node{
        public T item;
        public Node prev;
        public Node next;

        public Node(){
            item = null;
            prev = null;
            next = null;
        }

        public Node(T i, Node p, Node n){
            item = i;
            prev = p;
            next = n;
        }
    }

    /**
     * The empty constructor
     */
    public LinkedListDeque61B() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node currNode = sentinel.next;
        while (currNode != sentinel){
            list.add(currNode.item);
            currNode = currNode.next;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T thang = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return thang;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T thingy = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return thingy;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node currNode = sentinel.next;
        int ii = 0;
        while (ii < index){// ?: && currNode != sentinel
            currNode = currNode.next;
            ii += 1;
        }
        return currNode.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    /** Helper method of getRecursive() */
    private T getRecursive(int currIndex, Node currNode){
        if (currIndex == 0){
            return currNode.item;
        }
        return getRecursive(currIndex - 1, currNode.next);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof LinkedListDeque61B<?> otherDeque) {
            if (this.size != otherDeque.size()) {
                return false;
            }
            for (int ii = 0; ii < this.size; ii++) {
                if (this.get(ii) != otherDeque.get(ii)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.toList().toString();
    }

    public Iterator<T> iterator(){
        return new LinkedListDeque61BIterator();
    }

    private class LinkedListDeque61BIterator implements Iterator<T> {
        private Node ptr;

        LinkedListDeque61BIterator(){
            ptr = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return ptr != sentinel;
        }

        @Override
        public T next() {
            T thing = ptr.item;
            ptr = ptr.next;
            return thing;
        }
    }
}
