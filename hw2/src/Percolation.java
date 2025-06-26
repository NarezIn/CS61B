import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private WeightedQuickUnionUF grid;
    private boolean[][] isOpen;
    private final int length;
    private int isOpenCount;

    /**
     * Create an N-by-N grid, with all sites initially blocked, meaning filled with zeros
     * The top row and the bottom row are virtual to represent the top and bottom.
     **/
    public Percolation(int N) {
        if (N <= 0){
            throw new IllegalArgumentException("the argument N " + N + " must be greater than 0");
        }
        grid = new WeightedQuickUnionUF((N + 2) * N);
        isOpen = new boolean[N][N];
        length = N;
        isOpenCount = 0;
    }

    /**
     * Throws an exception if v1 is not a valid index.
     * Valid indices should be between 0 and N-1.
     * @param vertex - the index that we are checking
     * */
    private void validateIndex(int vertex) {
        if (vertex < 0 || vertex >= length){
            throw new IndexOutOfBoundsException("the argument vertex " + vertex + " is not between 0 and " + (length - 1));
        }
    }

    /**
     * Convert the 2D "coordinate" to 1D, while considering the virtual top.
     * @param row - x coordinate
     * @param col - y coordinate
     * */
    private int to1D(int row, int col){
        return (row + 1) * length + col;
        //(4, 4) -> (?29) actual: 5 * 5 + 4
    }

    /**
     * Connect the current site (row1, col1) with the open site nearby
     *
     * @param row1 and ...
     * @param col1 represent the coordinates of the site that we are opening, and it's always valid
     * @param row2 and ...
     * @param col2 represent the coordinates of the nearby sites, and it may not be valid, so check it.
     */
    private void connectIfOpen(int row1, int col1, int row2, int col2){
        if (row2 >= 0 && row2 < length && col2 >= 0 && col2 < length){
            if (isOpen[row2][col2]){
                grid.union(to1D(row1, col1), to1D(row2, col2));
                //If (row2, col2) is located at the top row or (row1, col1) is already "Full",
                //connect it with the virtual top, meaning "Full".
                if (row2 == 0){
                    grid.union(to1D(row2, col2), to1D(-1, col2));
                }
                else if (isFull(row1, col1)){
                    grid.union(to1D(row2, col2), grid.find(to1D(row1, col1)));
                }//check this block and isFull().... You were here !!!
            }
        }
    }

    public void open(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        int ii = to1D(row, col);
        if (isOpen[row][col]){
            return;
        }
        isOpen[row][col] = true;
        //If this is the top row, connect this site with the virtual top, meaning "Full".
        //If this is the bottom row, connect this site with the virtual bottom
        if (row == 0) {
            grid.union(ii, to1D(-1, col));
        }
        else if(row == length - 1){
            grid.union(ii, to1D(length, col));
        }
        connectIfOpen(row, col, row, col - 1);//the left site
        connectIfOpen(row, col, row - 1, col);//the up site
        connectIfOpen(row, col, row, col + 1);//the right site
        connectIfOpen(row, col, row + 1, col);//the down site
        isOpenCount += 1;
    }

    /*If site is open, it should be true, otherwise false. */
    public boolean isOpen(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        return isOpen[row][col];
    }

    public boolean isFull(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        return grid.connected(grid.find(to1D(row, col)), to1D(-1, col));
    }

    public int numberOfOpenSites() {
        return isOpenCount;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
