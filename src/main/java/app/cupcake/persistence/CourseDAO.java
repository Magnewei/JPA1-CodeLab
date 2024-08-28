package app.cupcake.persistence;

import app.cupcake.HibernateConfig;
import app.cupcake.entities.Course;
import app.cupcake.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CourseDAO implements iDAO<Course>{

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    @Override
    public void create(Course course) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        }
    }


    public void deleteById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE Course s WHERE s.id = :id").setParameter("id", id).executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Course course) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(course);
            em.getTransaction().commit();
            System.out.println("\n Deletion successful  \n");
        }
    }

    @Override
    public Course getById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Course course = em.find(Course.class, id);
            em.getTransaction().commit();

            return course;
        }
    }

    @Override
    public Set<Course> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<Course> query = em.createQuery("SELECT s FROM Course s", Course.class);

            em.getTransaction().commit();

            return query.getResultList().stream().collect(Collectors.toSet());

        }
    }

    @Override
    public void update(Course course) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(course);
            em.getTransaction().commit();
            System.out.println("\n Update successfull  \n");
        }
    }

    public List<String> getALlStudentsForCourses(int courseId){
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            List<Student> allStudents = query.getResultList();

            return allStudents.stream()
                    .filter(student -> student.getCourseID() != null && student.getCourseID().contains(courseId))
                    .map(Student::getName)
                    .collect(Collectors.toList());
        }
}
}
