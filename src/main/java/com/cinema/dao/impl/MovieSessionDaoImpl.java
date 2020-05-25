package com.cinema.dao.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exeption.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.MovieSession;
import com.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.time.LocalDate;
import java.util.List;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(movieSessionId);
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
            Query query = session.createQuery("from MovieSession");
            List<MovieSession> allMovieSessions = query.list();
            return allMovieSessions;
//            CriteriaQuery<MovieSession> criteriaQuery = session
//                    .getCriteriaBuilder().createQuery(MovieSession.class);
//            criteriaQuery.from(MovieSession.class);
//            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get All movie sessions from db", e);
        }
    }


    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session
                    .createQuery("from MovieSession where movie.id = :id "
                            + "AND showTime > :time");
            query.setParameter("id", movieId);
            query.setParameter("time", date.atStartOfDay());
            return (List<MovieSession>) query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get "
                    + "movie session by the date or movie id from db", e);
        }
    }
}
