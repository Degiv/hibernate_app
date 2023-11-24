package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
        configuration.addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Actor actor1 = new Actor("Daniel Radcliff");
            Actor actor2 = new Actor("Ralph Fiennes");

            Movie movie1 = new Movie("Harry Potter 3");
            Movie movie2 = new Movie("Harry Potter 6");

            movie1.setActors(new ArrayList<>(List.of(actor1, actor2)));
            movie2.setActors(new ArrayList<>(List.of(actor1, actor2)));

            actor1.setMovies(new ArrayList<>(List.of(movie1, movie2)));
            actor2.setMovies(new ArrayList<>(List.of(movie1, movie2)));

            session.persist(actor1);
            session.persist(actor2);
            session.persist(movie1);
            session.persist(movie2);

            session.getTransaction().commit();
        }
    }
}
