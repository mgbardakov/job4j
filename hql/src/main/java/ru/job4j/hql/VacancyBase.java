package ru.job4j.hql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class VacancyBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "vacancyBase",
            cascade = CascadeType.ALL)
    private List<Vacancy> vacancyList = new ArrayList<>();

    public VacancyBase() {
    }

    public VacancyBase(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }

    public void setVacancyList(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancyList.add(vacancy);
        vacancy.setVacancyBase(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyBase that = (VacancyBase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VacancyBase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vacancyList=" + vacancyList +
                '}';
    }
}
