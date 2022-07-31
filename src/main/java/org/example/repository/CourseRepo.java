package org.example.repository;

import org.example.config.DataBaseConectyvi;
import org.example.model.Course;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CourseRepo implements AutoCloseable {

    private final EntityManagerFactory entityManagerFactory = DataBaseConectyvi.createEntityManagerFactory();

    public void saveCourse(Course newCourse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(newCourse);
        System.out.println("course added!!");

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void deleteCourseById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Course.class, id));

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void updateCourse(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(course);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Course> getAllCourse() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Course> courses = entityManager.createQuery("select c from Course c", Course.class).getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();

        return courses;
    }


    public Course getCourseById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Course course = entityManager.createQuery("select c from Course c where c.course_id = ?1", Course.class)
                .setParameter(1, id)
                .getSingleResult();

        entityManager.getTransaction().commit();

        entityManager.close();

        return course;
    }

    public Course getCourseByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Course course = entityManager.createQuery("select c from Course c where c.creatAt = ?1", Course.class)
                .setParameter(1, name)
                .getSingleResult();
        entityManager.getTransaction().commit();

        entityManager.close();

        return course;
    }






    @Override
    public void close() throws Exception {
       entityManagerFactory.close();
    }
}
