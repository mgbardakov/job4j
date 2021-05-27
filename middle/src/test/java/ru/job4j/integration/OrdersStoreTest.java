package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private final BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true;");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(3);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] queries = builder.toString().split(";");
        for (int i = 0; i < queries.length - 1; i++) {
            pool.getConnection().prepareStatement(queries[i]).executeUpdate();
        }
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndFindItById() {
        OrdersStore store = new OrdersStore(pool);
        var order = Order.of("name1", "description1");
        store.save(order);
        var rsl = store.findById(1);
        assertThat(rsl, is(order));
    }

    @Test
    public void whenSaveOrderThenUpdateItAndFindById() {
        OrdersStore store = new OrdersStore(pool);
        var order = Order.of("name1", "description1");
        store.save(order);
        var newOrder = Order.of("changedName", "changedDescription");
        newOrder.setId(order.getId());
        store.updateOrder(newOrder);
        var updatedOrder = store.findById(1);
        assertThat(updatedOrder.getName(), is("changedName"));
        assertThat(updatedOrder.getDescription(), is("changedDescription"));
    }

    @Test
    public void whenSaveOrderAndFindItByName() {
        OrdersStore store = new OrdersStore(pool);
        var order = Order.of("name1", "description1");
        store.save(order);
        var rsl = store.findByName("name1");
        assertThat(rsl, is(order));
    }

}