package org.example.config;

import org.example.model.Course;
import org.example.model.Instructor;
import org.example.model.Lesson;
import org.example.model.Task;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

public class DataBaseConectyvi {

    public static EntityManagerFactory createEntityManagerFactory() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, "org.postgresql.Driver");
        properties.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        properties.setProperty(Environment.USER, "postgres");
        properties.setProperty(Environment.PASS, "1234");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.SHOW_SQL, "true");

        Configuration configuration = new Configuration();

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(Lesson.class);
        configuration.addAnnotatedClass(Task.class);

        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
