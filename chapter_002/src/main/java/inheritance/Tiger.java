package inheritance;

public class Tiger extends Predator {
    public Tiger() {
        System.out.println("Tiger");
    }

    public Tiger(String name) {
        super(name);
    }

    public static void main(String[] args) {
        Tiger tiger = new Tiger();
    }
}
