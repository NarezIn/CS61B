import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private WeightedQuickUnionUF grid;
    private boolean[] isOpen;
    private final int length;

    /*Create an N-by-N grid, with all sites initially blocked, meaning filled with zeros
    * Maybe put an extra row at the top and the bottom.*/
    public Percolation(int N) {
        if (N <= 0){
            throw new IllegalArgumentException("the argument N " + N + " must be greater than 0");
        }
        grid = new WeightedQuickUnionUF(N * N);
        isOpen = new boolean[N * N];
        length = N;
    }

    /*Throws an exception if v1 is not a valid index.
    * Valid indices should be between 0 and N-1. */
    private void validateIndex(int vertex) {
        if (vertex < 0 || vertex >= length){
            throw new IndexOutOfBoundsException("the argument vertex " + vertex + " is not between 0 and " + (grid.length - 1));
        }
    }

    /*Convert the 2D "coordinate" to 1D */
    private int to1D(int row, int col){
        return row * length + col;
    }

    public void open(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        isOpen[to1D(row, col)] = true;
    }

    /*If site is open, it should be true, otherwise false. */
    public boolean isOpen(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        return isOpen[to1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        validateIndex(row);
        validateIndex(col);
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return 0;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
