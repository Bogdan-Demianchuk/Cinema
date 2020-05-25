package com.cinema.dao.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exeption.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.MovieSession;
import com.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert movie session to db", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session
                    .createQuery("from MovieSession", MovieSession.class);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get All movie sessions from db", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session
                    .createQuery("from MovieSession where movie.id = :id "
                            + "AND showTime BETWEEN "
                            + ":beginningOfTheDay and :endOfTheDay", MovieSession.class);
            query.setParameter("id", movieId);
            query.setParameter("beginningOfTheDay", date.atTime(0, 0, 0));
            query.setParameter("endOfTheDay", date.atTime(23, 59, 59));
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get "
                    + "movie session by the date or movie id from db", e);
        }
    }
}
