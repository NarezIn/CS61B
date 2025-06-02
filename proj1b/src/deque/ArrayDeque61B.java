package deque;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    // may use Math.floorMod(int a, int b), always return the non-negative value of
    // a mod b.

    /** The empty constructor */
    public ArrayDeque61B(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 6;
        nextLast = 7;
    }
    @Override
    public void addFirst(T x) {
        size += 1;
        resizeUp();
        items[nextFirst--] = x;
        if(nextFirst < 0){// After pseudo-updating nextFirst, check if it's still a valid index.
            nextFirst = items.length - 1;// if not, make it the end of the array. (circular deque)
        }
    }

    @Override
    public void addLast(T x) {
        size += 1;
        resizeUp();
        items[nextLast++] = x;
        if(nextLast >= items.length){
            nextLast = 0;//same idea as in addFirst()
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i = 0; i < size; i++){
            returnList.add(this.get(i));
        }
        return returnList;
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
        resizeDown();
        size -= 1;
        int First = (nextFirst + 1 >= items.length) ? 0 : nextFirst + 1;
        T thing = items[First];
        items[First] = null;
        nextFirst = First;
        return thing;
    }

    @Override
    public T removeLast() {
        resizeDown();
        size -= 1;
        int Last = (nextLast - 1 < 0) ? items.length - 1 : nextLast - 1;
        T thing = items[Last];
        items[Last] = null;
        nextLast = Last;
        return thing;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[Math.floorMod(nextFirst + index + 1, items.length)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    /**
     * Resize up the ArrayDeque when this.size reaches the length of items.
     * This method should go after updating the size. */
    public void resizeUp(){
        if (size > items.length) {
            T[] newItems = (T[]) new Object[items.length * 2];
            for (int i = 0; i < items.length; i++) {
                newItems[i] = this.get(i);
            }
            nextFirst = newItems.length - 1;
            nextLast = items.length;
            items = newItems;
        }
    }

    /**
     * Resize down the ArrayDeque when this.size reaches below 25% of items.length,
     * and items.length is at least 16.
     * This method should go after updating the size. */
    public void resizeDown(){
        resizeDown(size - 1);
    }

    /**
     * Helper method of resizeDown
     * @param hypoSize: what the size would be after the removeFirst/removeLast operation.
     */
    private void resizeDown(int hypoSize){
        if (items.length >= 16 && hypoSize < (items.length / 4)) {
            T[] newItems = (T[]) new Object[items.length / 2];
            for (int i = 0; i < size; i++) {
                newItems[i] = this.get(i);
            }
            nextFirst = newItems.length - 1;
            nextLast = size; //items.length;
            items = newItems;
        }
    }
}
