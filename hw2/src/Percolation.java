import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final WeightedQuickUnionUF gridForPercolation;
    private final WeightedQuickUnionUF gridForFull;
    private final boolean[][] isOpen;
    private final int length;
    private int isOpenCount;
    private final int virtualTop;
    private final int virtualBottom;

    /**
     * Create an N-by-N grid, with all sites initially blocked, meaning filled with zeros
     * There are 1 virtual top site and 1 virtual bottom site - to handle the backwash problem.
     **/
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("the argument N " + N + " must be greater than 0");
        }
        gridForPercolation = new WeightedQuickUnionUF(N * N + 2);
        gridForFull = new WeightedQuickUnionUF(N * N + 2);
        isOpen = new boolean[N][N];
        length = N;
        isOpenCount = 0;
        virtualTop = 0;
        virtualBottom = N * N + 1;
    }

    /**
     * Throws an exception if v1 is not a valid index.
     * Valid indices should be between 0 and N-1.
     *
     * @param vertex - the index that we are checking
     */
    private void validateIndex(int vertex) {
        if (vertex < 0 || vertex >= length) {
            throw new IndexOutOfBoundsException("the argument vertex " + vertex + " is not between 0 and " + (length - 1));
        }
    }

    /**
     * Convert the 2D "coordinate" to 1D, while considering the virtual top.
     *
     * @param row - x coordinate
     * @param col - y coordinate
     */
    private int to1D(int row, int col) {
        return row * length + col + 1;
    }

    /**
     * Connect the current site (row1, col1) with the site (row2, col2) nearby if it is open.
     * If the site nearby is full, the current site would be full.
     * And if the current that is made full is at the actual last row, make percolation.
     *
     * @param row1 and ...
     * @param col1 represent the coordinates of the site that we are opening, and it's always valid
     * @param row2 and ...
     * @param col2 represent the coordinates of the nearby sites, and it may not be valid, so check it.
     */
    private void connectIfOpen(int row1, int col1, int row2, int col2) {
        if (row2 >= 0 && row2 < length && col2 >= 0 && col2 < length) {
            if (isOpen[row2][col2]) {
                gridForPercolation.union(to1D(row1, col1), to1D(row2, col2));
                gridForFull.union(to1D(row1, col1), to1D(row2, col2));
            }
        }
    }

    public void open(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        int ii = to1D(row, col);
        if (isOpen[row][col]) {
            return;
        }
        isOpen[row][col] = true;
        //If this is the top row, connect this site with the virtual top, meaning make it "Full".
        if (row == 0) {
            gridForPercolation.union(ii, virtualTop);
            gridForFull.union(ii, virtualTop);
        }
        //If this is the bottom row, connect this site with the bottom site. (only in gridForPerc)
        if (row == length - 1) {
            gridForPercolation.union(ii, virtualBottom);
        }
        connectIfOpen(row, col, row, col - 1);//the left site
        connectIfOpen(row, col, row - 1, col);//the up site
        connectIfOpen(row, col, row, col + 1);//the right site
        connectIfOpen(row, col, row + 1, col);//the down site
        isOpenCount += 1;
    }

    public boolean isOpen(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        return isOpen[row][col];
    }

    public boolean isFull(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        return gridForFull.connected(virtualTop, to1D(row, col));
    }

    public int numberOfOpenSites() {
        return isOpenCount;
    }

    public boolean percolates() {
        return gridForPercolation.connected(virtualTop, virtualBottom);
    }
}
