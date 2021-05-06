package ru.job4j.hibernate.lazy;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.lazy.model.CarBrand;
import ru.job4j.hibernate.lazy.model.CarModel;

import java.util.List;


public class HmbRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            var nissan = new CarBrand("Nissan");
            nissan.addCarModel(new CarModel("Almera"));
            nissan.addCarModel(new CarModel("Terrano"));
            nissan.addCarModel(new CarModel("Qashqai"));
            nissan.addCarModel(new CarModel("Murano"));
            nissan.addCarModel(new CarModel("X-Trail"));
            saveObject(nissan, sf);
            var list = getCarBrandList(sf);
            list.forEach(System.out::println);

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    private static void saveObject(Object object, SessionFactory sf) {
        var session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            session.persist(object);
            transaction.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private static List<CarBrand> getCarBrandList(SessionFactory sf) {
        List<CarBrand> rslList;
        var session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            rslList = session.createQuery(
                    "select distinct c from CarBrand c join fetch c.carModels"
            ).list();
            transaction.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
        return rslList;
    }
}
