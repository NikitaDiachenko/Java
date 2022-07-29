import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter first integer: ");
        int a = in.nextInt();
        System.out.println("Enter second integer: ");
        int b = in.nextInt();
        System.out.println("Enter third integer: ");
        int c = in.nextInt();
        if(a < 19 && a > 13 && b < 19 && b > 13 && c < 19 && c > 13){
            System.out.println("Small");

        }
        else
            System.out.println("Do not know");
    }
}
