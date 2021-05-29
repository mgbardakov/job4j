package ru.job4j.hql;

import javax.persistence.*;

@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "vacancy_base_id")
    private VacancyBase vacancyBase;

    public Vacancy() {
    }

    public Vacancy(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VacancyBase getVacancyBase() {
        return vacancyBase;
    }

    public void setVacancyBase(VacancyBase vacancyBase) {
        this.vacancyBase = vacancyBase;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
