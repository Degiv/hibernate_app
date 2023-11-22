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

            Person personToRename = session.get(Person.class, 1);
            personToRename.setName("Ivan");

            Person personToRemove = session.get(Person.class, 1);
            session.remove(personToRemove);

            Person person = new Person("qwe", 123);
            session.persist(person);
            System.out.println(person.getId());

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
