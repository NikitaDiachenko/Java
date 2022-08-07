import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Cat cat = new Cat();
    Cat cat1 = new Cat();
    Cat cat2 = new Cat();
    cat.animalMove();
    cat.animalMove(4);
    cat1.animalMove(6);
    System.out.println("Number of cats: " + Cat.catCounter);
    }
}
