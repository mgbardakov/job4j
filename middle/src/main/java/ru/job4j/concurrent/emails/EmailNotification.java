package ru.job4j.concurrent.emails;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * email notificator
 * @author mbardakov
 * @since 28.10.2020
 */
public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    /**
     * sends email to user
     * @param user - destination user
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            var subject = String.format("Notification %s to email %s",
                    user.getUsername(), user.getEmail());
            var body = String.format("Add a new event to %s",
                    user.getUsername());
            send(subject, body, user.getEmail());
        });

    }

    /**
     * sends email by user data
     * @param subject - notification subject
     * @param body - email text
     * @param email - email address
     */
    public void send(String subject, String body, String email) {

    }

    /**
     * closes thread pool
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(new User("Carl", "carl@carl.com"));
        emailNotification.close();
    }
}
