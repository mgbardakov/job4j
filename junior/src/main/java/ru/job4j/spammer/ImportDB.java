package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * class for import information from text file to database
 * @author mbardakov
 * @since 15.06.2020
 */
public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * loads users from text file
     * @return list of users
     * @throws IOException if file not found
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        Pattern pattern = Pattern.compile(";");
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(x -> users.add(new User(pattern.split(x)[0],
                                                      pattern.split(x)[1])));
        }
        return users;
    }

    /**
     * saves user to database
     * @param users list of users to save
     * @throws ClassNotFoundException if there's no driver for database
     * @throws SQLException sql exception
     */
    public void save(List<User> users) throws ClassNotFoundException,
                                                        SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "INSERT INTO users (name, email) VALUES (?, ?)")
                ) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream(
                "./junior/src/main/resources/app.properties")
        ) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(
                cfg, "./junior/src/main/resources/dump.txt"
        );
        db.save(db.load());
    }
}