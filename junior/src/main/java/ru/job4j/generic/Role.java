package ru.job4j.generic;

import java.util.Objects;

public class Role extends Base {
    String title;

    public Role(String id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(title, role.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }
}
