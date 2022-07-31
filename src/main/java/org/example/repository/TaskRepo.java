package org.example.repository;

import org.example.config.DataBaseConectyvi;
import org.example.model.Course;
import org.example.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TaskRepo implements AutoCloseable {

    private final EntityManagerFactory entityManagerFactory = DataBaseConectyvi.createEntityManagerFactory();

    public void saveTask(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(task);
        System.out.println("task added!!");

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void deleteTaskById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Task.class, id));

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void updateTasks(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(task);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Task> getAllTasks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Task> t = entityManager.createQuery("select t from Task t", Task.class).getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();

        return t;
    }


    public Task getTaskById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Task t = entityManager.createQuery("select t from Task t where t.task_id = ?1", Task.class)
                .setParameter(1, id)
                .getSingleResult();

        entityManager.getTransaction().commit();

        entityManager.close();

        return t;
    }


    @Override
    public void close() throws Exception {

        entityManagerFactory.close();
    }

}
