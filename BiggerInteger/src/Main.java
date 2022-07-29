import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int a,b;
    System.out.println("Enter first integer: ");
    a = in.nextInt();
    System.out.println("Enter second integer: ");
    b = in.nextInt();
    if (a > b){
        System.out.println("Bigger integer is: " + a);
    }
    else
        System.out.println("Bigger integer is: " + b);

    }
}
