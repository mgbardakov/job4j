package ru.job4j.hibernate.library.runtime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.hibernate.library.model.Author;
import ru.job4j.hibernate.library.model.Book;

import java.util.Arrays;
import java.util.function.Consumer;

public class HbmRun {

    protected static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        var necronomicon = new Book("Necronomicon");
        var knifeFight = new Book("My first knife fight");
        var capital = new Book("Das Capital");

        var jenkins = new Author("Adolf Jenkins");
        jenkins.addBook(necronomicon);
        jenkins.addBook(knifeFight);

        var frost = new Author("Gustav Frost");
        frost.addBook(knifeFight);
        frost.addBook(capital);

        saveObjects(jenkins, frost);

        deleteObject(Author.class, 1);
    }

    private static void txCons(final Consumer<Session> command) {
        var session = SESSION_FACTORY.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            command.accept(session);
            transaction.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private static void saveObjects(Object... object) {
        txCons(session -> Arrays.stream(object).forEach(session::persist));
    }

    private static <T> void deleteObject(Class<T> clazz, int id) {
        txCons(session -> {
            var object = session.get(clazz, id);
            session.remove(object);
        });
    }

}
