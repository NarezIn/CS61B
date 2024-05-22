//import java.util.Arrays;
public class LeapYear {

    /*
     * determine whether a year is a leap year
     * A leap year is...
     * divisible by 400 or
     * divisible by 4 and not by 100.
     * @param the year to be analyzed
     */
    public static boolean isLeapYear(int year){
        boolean deci = false;
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
            return true;
        }
        return deci;
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(args));
        //The line above is just a record of me studying.
        if (args.length < 1) {
            System.out.println("Please enter command line arguments.");
            System.out.println("e.g. java Year 2000");
        }
        for (int i = 0; i < args.length; i++) {
            try {
                int year = Integer.parseInt(args[i]);
                if (isLeapYear(year)){
                    System.out.println(year + " is a leap year.");
                }
                else{
                    System.out.println(year + " is not a leap year.");
                }
            } catch (NumberFormatException e) {
                System.out.printf("%s is not a valid number.\n", args[i]);
            }
        }
    }
}
