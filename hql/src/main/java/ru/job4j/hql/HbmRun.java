package ru.job4j.hql;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            loadData(sf);
            System.out.println(findAll(sf));
            System.out.println("--------------");
            var candidate1 = new Candidate();
            candidate1.setId(1);
            System.out.println(findById(candidate1, sf));
            System.out.println("--------------");
            var candidate2 = new Candidate("Jim", 3, 2000);
            candidate2.setId(1);
            updateCandidate(candidate2, sf);
            System.out.println(findAll(sf));
            System.out.println("--------------");
            var candidate3 = new Candidate();
            candidate3.setId(2);
            removeCandidate(candidate3, sf);
            System.out.println(findAll(sf));
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void loadData(SessionFactory sf) {
        var session = sf.openSession();
        session.beginTransaction();
        var vacList = List.of(new Vacancy("Junior Java Developer"),
                                           new Vacancy("Middle C# developer"),
                                            new Vacancy("Janitor"));
        var vacBase = new VacancyBase("hh.ru");
        vacList.forEach(vacBase::addVacancy);
        session.persist(vacBase);
        session.persist(new Candidate("Jim", 2, 1500, vacBase));
        session.persist(new Candidate("John", 3, 2000, vacBase));
        session.persist(new Candidate("James", 4, 2500, vacBase));
        session.getTransaction().commit();
        session.close();
    }

    private static Candidate findById(Candidate candidate, SessionFactory sf) {
        var session = sf.openSession();
        session.beginTransaction();
        var rsl = session.createQuery("from Candidate c join fetch c.vacancyBase b" +
                " join fetch b.vacancyList where c.id = :fId")
                .setParameter("fId", candidate.getId()).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return (Candidate) rsl;
    }

    private static void updateCandidate(Candidate candidate, SessionFactory sf) {
        var session = sf.openSession();
        session.beginTransaction();
        session.createQuery("update Candidate c set c.experience = :fExp," +
                " c.name = :fName, c.salary = :fSalary where c.id = :fId")
                .setParameter("fId", candidate.getId())
                .setParameter("fExp", candidate.getExperience())
                .setParameter("fName", candidate.getName())
                .setParameter("fSalary", candidate.getSalary())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    private static void removeCandidate(Candidate candidate, SessionFactory sf) {
        var session = sf.openSession();
        session.beginTransaction();
        session.createQuery("delete from Candidate c where c.id = :fId")
                .setParameter("fId", candidate.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    private static List<Candidate> findAll(SessionFactory sf) {
        var session = sf.openSession();
        session.beginTransaction();
        var rslList = session.createQuery(
                "select distinct c from Candidate c join fetch c.vacancyBase b" +
                        " join fetch b.vacancyList", Candidate.class).list();
        session.getTransaction().commit();
        session.close();
        return rslList;
    }

}
