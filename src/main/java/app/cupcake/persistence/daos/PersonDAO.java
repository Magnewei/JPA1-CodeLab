package app.cupcake.persistence.daos;

import app.cupcake.HibernateConfig;
import app.cupcake.persistence.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Purpose:
 *
 * @Author: Anton Friis Stengaard
 */
public class PersonDAO implements iDAO<Person> {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    @Override
    public void create(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }
    }

    @Override
    public Person getById(int id) {
        Person person;

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            person = (Person) em.createQuery("SELECT u FROM Person u WHERE u.id = :id", Person.class)
                    .setParameter("id", id)
                    .getSingleResult();

            em.persist(person);
            em.getTransaction().commit();

            return person;
        }
    }

    @Override
    public Set<Person> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);

            em.getTransaction().commit();

            return query.getResultList().stream().collect(Collectors.toSet());

        }
    }

    @Override
    public void update(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.createQuery("DELETE Person u WHERE u.id = :id").setParameter("id", id).executeUpdate();
        }
    }

}
