package app.cupcake.persistence;

import app.cupcake.HibernateConfig;
import app.cupcake.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Purpose:
 *
 * @Author: Anton Friis Stengaard
 */
public class StudentDAO implements iDAO<Student>{

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    @Override
    public void create(Student student) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Student student) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(student);
            em.getTransaction().commit();
            System.out.println("\n Deletion successful  \n");
        }

    }

    public void deleteById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.createQuery("DELETE Student s WHERE s.id = :id").setParameter("id", id).executeUpdate();
        }
    }

    @Override
    public Student getById(int id) {
        Student student;

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            student = em.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class)
                      .setParameter("id", id)
                      .getSingleResult();

            em.persist(student);
            em.getTransaction().commit();

            return student;
        }
    }

    @Override
    public Set getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);

            em.getTransaction().commit();

            return query.getResultList().stream().collect(Collectors.toSet());

        }
    }

    @Override
    public void update(Student student) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
            System.out.println("\n Deletion successful  \n");
        }
    }

    public List<String> getALlCoursesForStudent(int id){
        Student student = getById(id);
        return student.getCourseID().stream().map(String::valueOf).collect(Collectors.toList());
    }
}
