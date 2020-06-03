package ru.job4j.statistics;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * collection analyzer class
 * @author mbardakov
 * @since 03.06.2020
 */
public class Analyze {

    /**
     * collects differences between two lists
     * @param previous previous list
     * @param current current list
     * @return Info object
     */
    public Info diff(List<User> previous, List<User> current) {
        var previousMap = previous.stream().collect(Collectors.toMap(x -> x.id, x -> x));
        var currentMap = current.stream().collect(Collectors.toMap(x -> x.id, x -> x));
        int constantCount = 0;
        int changedCount = 0;
        for (Integer key : previousMap.keySet()) {
            User user = currentMap.get(key);
            if (user != null) {
                constantCount++;
                if (!user.name.equals(previousMap.get(key).name)) {
                    changedCount++;
                }
            }
        }
        return new Info(current.size() - constantCount, changedCount, previous.size() - constantCount);
    }

    /**
     * data model
     * @author mbardakov
     * @since 03.06.2020
     */
    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    /**
     * report class containing numbers of added, deleted and changed objects
     * @author mbardakov
     * @since 03.06.2020
     */
    public static class Info {

        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }

}
