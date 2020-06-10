package com.cinema.dao.impl;

import com.cinema.dao.MovieDao;
import com.cinema.exeption.DataProcessingException;
import com.cinema.model.Movie;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    private final SessionFactory sessionFactory;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert Movie to db", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery("from Movie", Movie.class);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get All movie ", e);
        }
    }

    @Override
    public Movie getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery
                    ("from Movie where id = :id", Movie.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get movie by id from db", e);
        }
    }
}

