package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person newPerson = new Person("Joshua", 62);
            Item newItem = new Item(newPerson, "Book");
            newPerson.setItems(new ArrayList<Item>());
            newPerson.getItems().add(newItem);

            session.persist(newPerson);
            session.persist(newItem);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
