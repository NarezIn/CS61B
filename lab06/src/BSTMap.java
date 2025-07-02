import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    /** Pairs of keys and values are stored in a BST of Node objects.
     *  This variable, root, stores the first pair in this BST. */
    private Node root;
    private int size;

    /** Represents one node in the BST that stores the key-value pairs. */
    private class Node {
        /** Stores the key of the key-value pair of this node in the list. */
        K key;
        /** Stores the value of the key-value pair of this node in the list. */
        V value;
        /** Stores the following left and right Node in the BST. */
        Node left;
        Node right;

        /** Stores k as the key, v as the value, an n as the next node in this Node
         * in the BST. */
        Node(K k, V v, Node n1, Node n2) {
            key = k;
            value = v;
            left = n1;
            right = n2;
        }

        /** Returns the Node in this BST of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        private Node getNode(K k) {
            if (k == null || this.key == null){
                return null;
            }
            if (k.compareTo(this.key) == 0) {
                return this;
            }
            else if (k.compareTo(this.key) < 0) {
                return this.left.getNode(k);
            }
            else{// (this.key.compareTo(k) > 0){
                return this.right.getNode(k);
            }
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        root = putPair(root, key, value);
    }

    /** Helper method for the put method. */
    private Node putPair(Node currNode, K key, V value) {
        if (currNode == null) {
            size += 1;
            return new Node(key, value, null, null);
        }
        else if (key.compareTo(currNode.key) == 0) {
            currNode.value = value;
        }
        else if (key.compareTo(currNode.key) < 0) {
            currNode.left = putPair(currNode.left, key, value);
        }
        else {
            currNode.right = putPair(currNode.right, key, value);
        }
        return currNode;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (root == null){
            return null;
        }
        return root.getNode(key).value;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (root == null){
            return false;
        }
        return root.getNode(key) != null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
        //Set<K> set = new HashSet<>();
        //return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
        //return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
        //return null;
    }

    /** Print out keys in the BST in increasing order. */
    public void printInOrder(){
        ArrayList<K> list = new ArrayList<>();
        constructInOrder(list, root);
        System.out.println(list);
    }

    /** Helper method of the printInOrder method */
    private void constructInOrder(ArrayList<K> list, Node currNode){
        if (currNode != null){
            constructInOrder(list, currNode.left);
            list.add(currNode.key);
            constructInOrder(list, currNode.right);
        }
    }
}