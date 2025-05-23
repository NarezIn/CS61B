package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 * Filled in by NarezIn:
 * I peeked at the instruction of the normal version to get some ideas.
 */
public class GameLogic {
    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // fill this in
        if (side == Side.NORTH) {
            tiltUp(board);
        } else if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
        } else { // SOUTH
            rotateRight(board);
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
            rotateLeft(board);
        }
    }

    /**
     * Given the index of the column,
     * moving tiles in that column all the way up
     *
     * @param board the current state of the board
     * @param iCol the destining index determined by the direction
     *
     */
    public static void moveUpCol(int[][] board, int iCol) {
        for (int ii = 0; ii < board.length; ii++) {
            if (board[ii][iCol] != 0) {
                for (int i = ii; i > 0; i--) {
                    if (board[i - 1][iCol] == 0) {
                        board[i - 1][iCol] = board[i][iCol];
                        board[i][iCol] = 0;
                    }
                }
            }
        }
    }

    /**
     * Given the board that has been moved up and the index of the column,
     * merging tiles in that column all the way up
     *
     * @param board the current state of the board
     * @param iCol the destining index determined by the direction
     *
     */
    public static void mergeUpCol(int[][] board, int iCol) {
        int numMerge = 0;
        for (int i = 0; i < board.length - 1; i++) {
            if (board[i][iCol] != 0 && board[i][iCol] == board[i + 1][iCol]) {
                numMerge++;
                i++;
            }
        }
        int kk = 0;
        while (numMerge > 0 && kk < board.length){
            if (board[kk][iCol] == board[kk + 1][iCol]) {
                board[kk][iCol] = board[kk][iCol] + board[kk][iCol];
                int jj = 1;
                while ((kk + jj) < board.length - 1) {
                    board[kk + jj][iCol] = board[kk + jj + 1][iCol];
                    jj++;
                }
                board[kk+jj][iCol] = 0;
                numMerge--;
            }
            else if (board[kk][iCol] == 0) {
                break;
            }
            kk++;
        }
    }

    /**
     * Traversing through every column to move every tile up.
     *
     * @param board the current state of the board
     */
    public static void tiltUp(int[][] board) {
        for (int ii = 0; ii < board.length; ii++) {
            moveUpCol(board, ii);
            mergeUpCol(board, ii);
        }
    }
}
