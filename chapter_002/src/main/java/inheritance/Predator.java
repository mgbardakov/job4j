package inheritance;

public class Predator extends Animal {
    public Predator() {
        System.out.println("Predator");
    }

    public Predator(String name) {
        super(name);
    }
}
