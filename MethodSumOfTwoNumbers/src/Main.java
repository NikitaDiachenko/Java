import java.util.Scanner;

public class Main {
    public static void sumOfTwoNumbers(int a, int b) {
        System.out.println("The sum of two numbers is: " + (a + b));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int a = scanner.nextInt();
        System.out.print("Enter second number: ");
        int b = scanner.nextInt();
        sumOfTwoNumbers(a, b);
    }
}
