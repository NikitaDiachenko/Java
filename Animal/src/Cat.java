public class Cat extends Animal{
    static int catCounter = 0;
    {
        catCounter += 1;
    }
    @Override
    public void animalMove() {
        System.out.println("Meow and run.");
    }
    public void animalMove(int kilometers) {
        if (kilometers > 5){
            System.out.println("I am tired.");
        }
        else super.animalMove();
    }
}
