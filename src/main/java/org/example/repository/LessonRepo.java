package org.example.repository;

import org.example.config.DataBaseConectyvi;
import org.example.model.Course;
import org.example.model.Instructor;
import org.example.model.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class LessonRepo implements AutoCloseable {

    private final EntityManagerFactory entityManagerFactory = DataBaseConectyvi.createEntityManagerFactory();

    public void addLessonsOnCourse(Long id,List<Lesson> lessonList) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class,id);
        course.setLessonList(lessonList);
    }

    public void deleteLessonsById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Lesson.class, id));

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void updateLessons(Lesson lesson) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(lesson);

        entityManager.getTransaction().commit();

        entityManager.close();
    }


    public List<Lesson> getAllLessonsWithIdCourse() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Lesson> l = entityManager.createQuery("select l from Lesson l", Lesson.class).getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();

        return l;
    }

    public Lesson getLessonsById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Lesson l = entityManager.createQuery("select l from Lesson l where l.lesson_id = ?1", Lesson.class)
                .setParameter(1, id)
                .getSingleResult();

        entityManager.getTransaction().commit();

        entityManager.close();

        return l;
    }

    @Override
    public void close() throws Exception {

        entityManagerFactory.close();
    }
}
