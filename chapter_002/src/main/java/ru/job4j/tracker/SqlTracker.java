package ru.job4j.tracker;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;
    private static final Logger LOG = LogManager.getLogger(
            SqlTracker.class.getName()
    );

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = cn.prepareStatement(
                "INSERT INTO item (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, item.getName());
            statement.executeUpdate();
            ResultSet rset = statement.getGeneratedKeys();
            if (rset.next()) {
                item.setId(String.valueOf(rset.getInt(1)));
            }
        } catch (SQLException e) {
            LOG.error("sql error", e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        var rsl = false;
        try (PreparedStatement statement = cn.prepareStatement(
                "UPDATE item SET name = ? WHERE id = ?")
        ) {
            statement.setString(1, item.getName());
            statement.setInt(2, Integer.parseInt(id));
            int affectedRows = statement.executeUpdate();
            rsl = affectedRows != 0;
        } catch (SQLException e) {
            LOG.error("sql error", e);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        var rsl = false;
        try (PreparedStatement statement = cn.prepareStatement(
                "DELETE FROM item WHERE id = ?")
        ) {
            statement.setInt(1, Integer.parseInt(id));
            var affectedRows = statement.executeUpdate();
            rsl = affectedRows != 0;
        } catch (SQLException e) {
            LOG.error("sql error", e);
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        var rsl = new ArrayList<Item>();
        try (Statement statement = cn.createStatement()) {
            try (var resultSet = statement.executeQuery(
                    "SELECT id, name FROM item")) {
                while (resultSet.next()) {
                    rsl.add(new Item(
                            resultSet.getString("id"),
                            resultSet.getString("name"))
                    );
                }
            }
        } catch (SQLException e) {
            LOG.error("sql error", e);
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        var rsl = new ArrayList<Item>();
        try (PreparedStatement statement = cn.prepareStatement(
                "SELECT id, name FROM item WHERE name = ?")) {
            statement.setString(1, key);
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rsl.add(new Item(
                            resultSet.getString("id"),
                            resultSet.getString("name"))
                    );
                }
            }
        } catch (SQLException e) {
            LOG.error("sql error", e);
        }
        return rsl;

    }

    @Override
    public Item findById(String id) {
        Item rsl = null;
        try (PreparedStatement statement = cn.prepareStatement(
                "SELECT id, name FROM item WHERE id = ?")) {
            statement.setInt(1, Integer.parseInt(id));
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rsl = new Item(
                            resultSet.getString("id"),
                            resultSet.getString("name")
                    );
                    LOG.debug(String.format("id: %s name: %s",
                            rsl.getId(), rsl.getName())
                    );
                }
            }
        } catch (SQLException e) {
            LOG.error("sql error", e);
        }
        return rsl;
    }
}