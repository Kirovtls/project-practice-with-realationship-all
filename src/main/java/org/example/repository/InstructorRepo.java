package org.example.repository;

import org.example.config.DataBaseConectyvi;
import org.example.model.Course;
import org.example.model.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class InstructorRepo implements AutoCloseable {

    private final EntityManagerFactory entityManagerFactory = DataBaseConectyvi.createEntityManagerFactory();

    public void saveInstructor(Instructor instructor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(instructor);
        System.out.println("instructor added!!");

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void deleteInstructorById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Instructor.class, id));

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void updateInstructor(Instructor instructor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(instructor);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Instructor> getAllInstructor() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Instructor> i = entityManager.createQuery("select i from Instructor i", Instructor.class).getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();

        return i;
    }


    public Instructor getInstructorById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Instructor i = entityManager.createQuery("select i from Instructor i where i.instructor_id = ?1", Instructor.class)
                .setParameter(1, id)
                .getSingleResult();

        entityManager.getTransaction().commit();

        entityManager.close();

        return i;
    }

    public void addCourseOnInstructor(Long id,List<Course> course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Instructor instructor = entityManager.find(Instructor.class,id);
        instructor.setCourseList(course);
    }
    @Override
    public void close() throws Exception {
       entityManagerFactory.close();
    }
}
