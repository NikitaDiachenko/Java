import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int a,b;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter first integer:");
        a = in.nextInt();
        System.out.println("Enter second integer: ");
        b = in.nextInt();
        System.out.println("Sum of two integers: " + (a + b));
    }
}
