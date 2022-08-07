import java.util.Scanner;

public class Main {
    public static boolean evenOrUneven (int a){
        return a % 2 == 0;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number to check it is even or uneven: ");
        int a = scanner.nextInt();
        System.out.println(evenOrUneven(a));
    }
}
