import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {
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
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
