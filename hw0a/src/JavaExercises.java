/**
 * Skeleton file for HW0A.
 * Exercises sourced from Practice-It by the University of Washington.
 * Original problems available at: https://practiceit.cs.washington.edu/
 *
 * @author Erik Kizior
 * filled in by NarezIn on 5/20/2025
 */
public class JavaExercises {

    /**
     * Prints a right-aligned triangle of stars ('*') with 5 lines.
     * The first row contains 1 star, the second 2 stars, and so on.
     */
    public static void starTriangle() {
        // TODO: Fill in this function
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5 - (i+1); j++){
                System.out.print(" ");
            }
            for (int k = 0; k < i + 1; k++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * Prints each character of a given string followed by its reverse index.
     * Example: printIndexed("hello") -> h4e3l2l1o0
     */
    public static void printIndexed(String s) {
        // TODO: Fill in this function
        // Let's try using while loop this time.
        int ii = 0;
        while (ii < s.length()) {
            System.out.print(s.charAt(ii));
            System.out.print(s.length() - (ii + 1));
            ii++;
        }
        System.out.println();
    }

    /**
     * Returns a new string where each character of the given string is repeated twice.
     * Example: stutter("hello") -> "hheelllloo"
     */
    public static String stutter(String s) {
        // TODO: Fill in this function
        StringBuilder deliver = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            deliver.append(s.charAt(i));
            deliver.append(s.charAt(i));
        }
        return deliver.toString();
    }

    /**
     * Determines the quadrant of a Cartesian coordinate (x, y).
     * Returns:
     *   1 for the first quadrant (x > 0, y > 0),
     *   2 for the second quadrant (x < 0, y > 0),
     *   3 for the third quadrant (x < 0, y < 0),
     *   4 for the fourth quadrant (x > 0, y < 0),
     *   0 if the point lies on an axis.
     */
    public static int quadrant(int x, int y) {
        // TODO: Fill in this function
        if (x == 0 || y == 0) {
            return 0;
        }
        else if (x > 0) {
            if (y > 0) {
                return 1;
            }
            else{//y < 0
                return 4;
            }
        }
        else{//x < 0
            if (y > 0) {
                return 2;
            }
            else{//y < 0
                return 3;
            }
        }
    }

    public static void main(String[] args) {
        starTriangle();
        printIndexed("hello");
        System.out.println(stutter("hello"));
        System.out.println(quadrant(3, 4));  // Output: 1
        System.out.println(quadrant(-3, 4)); // Output: 2
        System.out.println(quadrant(-3, -4));// Output: 3
        System.out.println(quadrant(3, -4)); // Output: 4
        System.out.println(quadrant(0, 5));  // Output: 0
    }
}
