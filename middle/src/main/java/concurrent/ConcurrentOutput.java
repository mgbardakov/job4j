package concurrent;

/**
 * example class for concurrency
 * @author mbardakov
 * @since 11.10.2020
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(() ->
            System.out.println(Thread.currentThread().getName()));
        Thread second = new Thread(() ->
                System.out.println(Thread.currentThread().getName()));
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
