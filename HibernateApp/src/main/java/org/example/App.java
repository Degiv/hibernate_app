package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person1 = new Person("John", 1);
            Person person2 = new Person("John2", 10);
            Person person3 = new Person("John3", 100);
            session.persist(person1);
            session.persist(person2);
            session.persist(person3);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
