package ru.job4j.tracker;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private StandardServiceRegistry registry;
    private SessionFactory sf;


    @Override
    public void init() {
        registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }

    @Override
    public Item add(Item item) {
        try (var session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        var rsl = false;
        item.setId(Integer.parseInt(id));
        try (var session = sf.openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        var rsl = false;
        try (var session = sf.openSession()) {
            var item = new Item();
            item.setId(Integer.parseInt(id));
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List rslList = null;
        try (var session = sf.openSession()) {
            session.beginTransaction();
            rslList = session.createQuery("from ru.job4j.tracker.Item").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rslList;
    }

    @Override
    public List<Item> findByName(String key) {
        List rslList = null;
        try (var session = sf.openSession()) {
            session.beginTransaction();
            var query = session.createQuery(
                    "from ru.job4j.tracker.Item item where item.name = :name");
            query.setParameter("name", key);
            rslList = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rslList;
    }

    @Override
    public Item findById(String id) {
        Item rslItem = null;
        try (var session = sf.openSession()) {
            session.beginTransaction();
            rslItem = session.get(Item.class, Integer.parseInt(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rslItem;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}