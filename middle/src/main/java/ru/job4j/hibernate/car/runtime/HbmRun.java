package ru.job4j.hibernate.car.runtime;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.car.model.car.CarBrand;
import ru.job4j.hibernate.car.model.car.CarModel;


public class HbmRun {


    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            var session = sf.openSession();
            session.beginTransaction();
            var nissan = new CarBrand("Nissan");
            nissan.addCarModel(new CarModel("Almera"));
            nissan.addCarModel(new CarModel("Terrano"));
            nissan.addCarModel(new CarModel("Qashqai"));
            nissan.addCarModel(new CarModel("Murano"));
            nissan.addCarModel( new CarModel("X-Trail"));
            session.save(nissan);
            session.getTransaction().commit();
            session.close();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}

