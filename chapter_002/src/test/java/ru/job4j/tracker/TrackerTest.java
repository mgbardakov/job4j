package ru.job4j.tracker;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (Store store = new SqlTracker(
                ConnectionRollback.create(this.init()))
        ) {
            Item item = new Item("test1");
            store.add(item);
            Item result = store.findById(String.valueOf(item.getId()));
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void trackerAdd() throws Exception {
        try (Store testStore = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item testItem = new Item("Сковорода");
            assertThat(testStore.add(testItem), is(testItem));
        }

    }

    @Test
    public void trackerFindAll() throws Exception {
        var connection = ConnectionRollback.create(this.init());
        try (Store testStore = new SqlTracker(connection)) {
            connection.createStatement().execute("DELETE FROM item");
            Item pan = testStore.add(new Item("Сковорода"));
            Item fork = testStore.add(new Item("Вилка"));
            Item mug = testStore.add(new Item("Кружка"));
            List<Item> result = new ArrayList<>();
            result.add(pan);
            result.add(fork);
            result.add(mug);
            assertThat(testStore.findAll(), is(result));
        }
    }

    @Test
    public void trackerFindByName() throws Exception {
        try (Store testStore = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item mug0 = testStore.add(new Item("Кружка"));
            Item mug = testStore.add(new Item("Кружка"));
            List<Item> result = new ArrayList<>();
            result.add(mug0);
            result.add(mug);
            assertThat(testStore.findByName("Кружка"), is(result));
        }
    }

    @Test
    public void trackerFindByID() throws Exception {
        try (Store testStore = new SqlTracker(
                ConnectionRollback.create(this.init()))
        ) {
            Item mug = testStore.add(new Item("Кружка"));
            assertThat(testStore.findById(String.valueOf(mug.getId())), is(mug));
        }
    }

    @Test
    public void trackerFindByIDIfTheresNoOneThenNull() {
        Store testStore = new SqlTracker();
        testStore.init();
        assertThat(testStore.findById("-1"), nullValue());
    }

    @Test
    public void whenReplace() throws Exception {
        try (Store store = new SqlTracker(
                ConnectionRollback.create(this.init()))
        ) {
            Item bug = new Item("Bug");
            store.add(bug);
            String id = String.valueOf(bug.getId());
            Item bugWithDesc = new Item("Bug with description");
            store.replace(id, bugWithDesc);
            assertThat(store.findById(id).getName(), is("Bug with description"));
        }
    }

    @Test
    public void whenDelete() throws Exception {
        try (Store store = new SqlTracker(
                ConnectionRollback.create(this.init()))
        ) {
            Item bug = new Item("Bug");
            store.add(bug);
            String id = String.valueOf(bug.getId());
            store.delete(id);
            assertThat(store.findById(id), is(nullValue()));
        }
    }

    @Test
    public void whenNoDeleteItem() {
        Store store = new SqlTracker();
        store.init();
        String id = "-1";
        assertThat(store.delete(id), is(false));
    }

    @Test
    public void whenNoReplaceItem() {
        Store store = new SqlTracker();
        store.init();
        String id = "-1";
        Item bugWithDesc = new Item("Bug with description");
        assertThat(store.replace(id, bugWithDesc), is(false));
    }
}
