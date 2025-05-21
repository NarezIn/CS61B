import java.awt.desktop.SystemSleepEvent;

public class Dessert {
    private int flavor;
    private int price;
    public static int total = 0;

    public Dessert(int flavor, int price) {
        this.flavor = flavor;
        this.price = price;
        total++;
    }

    public void printDessert(){
        System.out.println(flavor + " " + price + " " + total);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
